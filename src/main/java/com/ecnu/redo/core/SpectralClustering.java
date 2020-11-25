package com.ecnu.redo.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SpectralClustering {
    public static String refactor(double[][] A) {
        int n = A.length;
        // 创建文件
        Path fpath = Paths.get("src/main/resources/dsm_matrix.txt");
        if (!Files.exists(fpath)) {
            try {
                Files.createFile(fpath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建BufferedWriter
        try {
            BufferedWriter bfw = Files.newBufferedWriter(fpath);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bfw.write(A[i][j] + " ");
                }
                bfw.write("\n");
            }
            bfw.flush();
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 调用Python脚本
        String res = "";
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python src/main/resources/spec.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            res = in.readLine(); // 这个脚本只会输出一行结果，直接拿到这个结果
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return res;
    }
}
