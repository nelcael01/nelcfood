package com.nelcfood.util;

import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class LeituraDeJson {
  public static String getConteudoJson(String nomeArquivo) {
    try {
      InputStream stream = ResourceUtils.class.getResourceAsStream(nomeArquivo);
      return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }
}
