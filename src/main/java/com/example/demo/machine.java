package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class machine {

    private static String user1ID="Jenny";
    private static String user2ID="Winter";
    public static void main(String[] args)
            throws IOException,    InterruptedException {
        String new_group = user1ID+","+user2ID;
        //String new_user = id+","+life_style+","+relation+","+rest_style+","+share+","+shower+","+clean+","+guest;
        //String[] new_user = {id, life_style, relation, rest_style, share, shower, clean, guest};
        String[] command = new String[3];
        command[0]="python.exe";
        command[1]="C:/Users/user/PycharmProjects/IPS/main.py";
        command[2]=new_group;
        DemoApplication runner = new DemoApplication();
        runner.byRuntime(command);
        runner.byProcessBuilder(command);
        runner.byProcessBuilderRedirect(command);
    }

    public void byRuntime(String[] command)
            throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        printStream(process);
    }

    public void byProcessBuilder(String[] command)
            throws IOException,InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();
        printStream(process);
    }

    private void printStream(Process process)
            throws IOException, InterruptedException {
        process.waitFor();
        try (InputStream psout = process.getInputStream()) {
            copy(psout, System.out);
        }
    }

    public void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int n = 0;
        while ((n = input.read(buffer)) != -1) {
            output.write(buffer, 0, n);
        }
    }
    public void byProcessBuilderRedirect(String[] command)
            throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        builder.redirectError(ProcessBuilder.Redirect.INHERIT);
        builder.start();
    }
}
