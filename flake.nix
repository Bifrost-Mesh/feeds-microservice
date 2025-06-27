{
  description = "Feeds Microservice development environment";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils, ... }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs {
          inherit system;

          config.allowUnfree = true;
        };

        jdk = pkgs.jdk24_headless;
      in with pkgs; {
        devShells.default = mkShell {
          nativeBuildInputs = [
            jdk
            (gradle.override { java = jdk; })
            (kotlin.override { jre = jdk; })
            graalvmPackages.graalvm-ce

            protobuf
          ];

          buildInputs = [ ];

          GRAALVM_HOME = graalvmPackages.graalvm-ce;
        };
      });
}
