{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs";

    flake-utils.url = "github:numtide/flake-utils";

    devshell = {
      url = "github:numtide/devshell";
      inputs.nixpkgs.follows = "nixpkgs";
      inputs.flake-utils.follows = "flake-utils";
    };
  };

  outputs = { self, nixpkgs, flake-utils, devshell, ... }:
    let
      forSystem = system:
        let
          pkgs = import nixpkgs {
            inherit system;
            overlays = [ devshell.overlays.default ];
          };
          jdk = pkgs.jdk11;
        in
        {
          devShell = pkgs.devshell.mkShell {
            name = "money-steward-shell";
            commands = [
              { package = pkgs.sbt.override { jre = jdk; }; }
            ];

            devshell.packages = [
              jdk
              pkgs.nixpkgs-fmt
            ];
          };
        };
    in
    { } // flake-utils.lib.eachDefaultSystem forSystem;
}
