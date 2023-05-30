{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixpkgs-unstable";

    flake-parts = {
      url = "github:hercules-ci/flake-parts";
      inputs.nixpkgs-lib.follows = "nixpkgs";
    };

    devshell = {
      url = "github:numtide/devshell";
      inputs.nixpkgs.follows = "nixpkgs";
    };
  };

  outputs = inputs@{ self, nixpkgs, flake-parts, devshell, ... }:

    flake-parts.lib.mkFlake { inherit inputs; } {
      imports = [ devshell.flakeModule ];

      systems = [
        "x86_64-linux"
        "x86_64-darwin"
        "aarch64-darwin"
      ];

      perSystem = { pkgs, system, ... }: {
        _module.args.pkgs = import inputs.nixpkgs {
          inherit system;
          overlays = [
            (final: prev: {
              jdk = prev.jdk17_headless;
              jre = prev.jdk17_headless;
            })
          ];
        };

        devshells.default = {
          name = "development-shell";

          commands = [
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

        packages.docs = pkgs.stdenv.mkDerivation {
          name = "money-steward-docs";
          src = ./.;
          nativeBuildInputs = [ (pkgs.python311.withPackages (ps: [ ps.mkdocs ps.mkdocs-material ])) ];
          buildPhase = "mkdocs build --site-dir $out";
          dontInstal = true;
        };

        devshells.docs = {
          name = "docs-shell";

          packages = [
            (pkgs.python311.withPackages (ps: [ ps.mkdocs ps.mkdocs-material ]))
          ];
        };
      };

    };
}
