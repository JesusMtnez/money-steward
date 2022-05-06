{
  description = "Money Steward";

  inputs= {
    nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
    poetry2nix = {
      url = "github:nix-community/poetry2nix";
      inputs.nixpkgs.follows = "nixpkgs";
      inputs.flake-utils.follows = "flake-utils";
    };
  };

  outputs = { self, nixpkgs, flake-utils , poetry2nix}:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs {
          inherit system;
          overlays = [ poetry2nix.overlay ];
        };

        python = pkgs.python39;

        packageName = "money_steward";

        app = pkgs.poetry2nix.mkPoetryApplication {
          projectDir = ./.;
          python = python;
        };
      in
      {
        packages.${packageName} = app;

        defaultPackage = self.packages.${system}.${packageName};

        devShell = pkgs.mkShell {
          buildInputs = with pkgs; [
            python
            poetry
          ];
        };
      });
}
