import java.util.stream.Stream;
import java.util.Arrays;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class ExecHelpers {
  static String streamToString(InputStream out) {
    Stream<String> lines = new BufferedReader(new InputStreamReader(out)).lines();
    String result = "";
    for(Object o: lines.toArray()) {
      result += (String)o + "\n";
    }
    return result;
  }
  static String exec(String[] cmd) throws IOException {
    Process p = new ProcessBuilder().command(Arrays.asList(cmd)).redirectErrorStream(true).start();
    InputStream out = p.getInputStream();
//    InputStream err = p.getErrorStream();
    return String.format("%s\n", streamToString(out));
  }
}

class Handler implements URLHandler {
    public String handleRequest(URI url) throws IOException {
       if (url.getPath().equals("/grade")) {
           String[] parameters = url.getQuery().split("=");
           if (parameters[0].equals("repo")) {
               return ExecHelpers.exec(new String[]{"bash", "grade.sh", parameters[1]});
           }
           else {
               return "Couldn't find query parameter repo";
           }
       }
       else {
           return "Don't know how to handle that path!";
       }
    }
}

class GradeServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}

