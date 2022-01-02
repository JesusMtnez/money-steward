{jdk ? "jdk11" }:

let
  nixpkgs = fetchTarball {
    name = "nixpkgs-21.11";
    url = "https://github.com/NixOS/nixpkgs/archive/release-21.11.tar.gz";
  };

  config = {
    packageOverrides = p: {
      sbt = p.sbt.override {
        jre = p.${jdk};
      };
      scalafmt = p.scalafmt.override {
        jdk = p.${jdk};
        jre = p.${jdk};
      };
    };
  };

  pkgs = import nixpkgs { inherit config; };

in
  pkgs.mkShell {
    buildInputs = [
      pkgs.sbt
      pkgs.scalafmt
    ];
  }
