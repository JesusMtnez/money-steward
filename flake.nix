{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixpkgs-unstable";

    flake-utils.url = "github:numtide/flake-utils";

    devshell = {
      url = "github:numtide/devshell";
      inputs.nixpkgs.follows = "nixpkgs";
      inputs.flake-utils.follows = "flake-utils";
    };
  };

  outputs = { self, nixpkgs, flake-utils, devshell, ... }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        jreOverlay = f: p: {
          jdk = p.jdk17_headless;
          jre = p.jdk17_headless;
        };

        pkgs = import nixpkgs {
          inherit system;
          overlays = [ devshell.overlays.default jreOverlay ];
        };
      in
      {
        devShells = rec {
          default = scala;

          scala = pkgs.devshell.mkShell {
            name = "scala-dev-shell";

            commands = [
              {
                name = "mkdocs";
                help = "fast, simple and downright gorgeous static site generator";
                package = (pkgs.python311.withPackages (ps: [ ps.mkdocs ps.mkdocs-material ]));
              }
              { package = pkgs.sbt; }
              { package = pkgs.yarn; }
            ];

            packages = [
              pkgs.jdk
              pkgs.nixpkgs-fmt
              pkgs.nodejs
            ];

            env = [
              { name = "JAVA_HOME"; value = "${pkgs.jdk}"; }
            ];
          };
        };
      }
    );
}
