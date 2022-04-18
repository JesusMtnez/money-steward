{
  description = "Money Steward";

  inputs= {
    nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
    poetry2nix.url = "github:nix-community/poetry2nix";
  };

  outputs = { self, nixpkgs, flake-utils, poetry2nix }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs {
          inherit system;
          # overlays = [ self.overlay ];
        };

        packageName = "money-steward";

        app = pkgs.poetry2nix.mkPoetryApplication {
          projectDir = ./.;
          python = pkgs.python310;
        };
      in
      {
        packages.${packageName} = app;

        defaultPackage = self.packages.${system}.${packageName};

        devShell = pkgs.mkShell {
          buildInputs = with pkgs; [
            poetry
          ];
        };
      });
}
