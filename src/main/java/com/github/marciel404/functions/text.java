package com.github.marciel404.functions;

import org.apache.commons.io.FileUtils;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

public class text {
    public static String leitor(String path, String[] args) throws IOException {

        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                if (args[3].equalsIgnoreCase("ticket")) {
                    BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
                    buffWrite.append(
                            String.format(
                                    "Data: %s\nid: %s\nNome no momento: %s\n",
                                    LocalDateTime.now(
                                                    ZoneId.of(
                                                            "America/Sao_Paulo"
                                                    )
                                            )
                                            .format(
                                                    DateTimeFormatter
                                                            .ofPattern("HH:mm:ss dd/MM/yyyy")
                                            ),
                                    args[0],
                                    args[1]
                            )
                    );
                    buffWrite.close();
                }

            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return FileUtils.readFileToString(new File(path), "UTF-8");

    }

    public static void escritor(String path, String escrever, String[] args) throws IOException {
        if (args[0].equalsIgnoreCase("listaevento")){

            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
            buffWrite.append(escrever);
            buffWrite.close();

        } else if (args[3].equalsIgnoreCase("ticket")) {

            String e = leitor(path, args) + "\n" + args[2] + ": "+ escrever;
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
            buffWrite.append(e);
            buffWrite.close();

        }

    }
}
