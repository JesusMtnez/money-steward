import { defineConfig } from "vite";
import scalaJSPlugin from "@scala-js/vite-plugin-scalajs";

export default defineConfig({
  plugins: [scalaJSPlugin( {
      cwd: '../../.', // path to the directory containing the sbt build
      projectID: 'webapp', // sbt project ID from within the sbt build to get fast/fullLinkJS from
  })],
});
