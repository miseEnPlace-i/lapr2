package app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * Source: https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
public class CustomScanner {
  BufferedReader br;
  StringTokenizer st;

  public CustomScanner(InputStream stream) {
    br = new BufferedReader(new InputStreamReader(stream));
  }

  public String next() {
    while (st == null || !st.hasMoreElements()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return st.nextToken();
  }

  public String nextLine() {
    String str = "";
    try {
      str = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }

  public boolean hasNextLine() {
    boolean hasNextLine = false;
    try {
      hasNextLine = br.ready();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return hasNextLine;
  }

  public void close() {
    try {
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
