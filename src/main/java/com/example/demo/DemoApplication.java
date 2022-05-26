package com.example.demo;

import org.apache.commons.exec.CommandLine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class DemoApplication {

    private static String id = "hello";
	private static String relation = "family";
	private static String rest_style = "out";
	private static String life_style = "day";
	private static String share = "Y";
	private static String shower = "morning";
	private static String clean = "now";
	private static String guest = "N";

	public static void main(String[] args)
			throws IOException,    InterruptedException {
		String new_user = id+","+life_style+","+relation+","+rest_style+","+share+","+shower+","+clean+","+guest;
		//String[] new_user = {id, life_style, relation, rest_style, share, shower, clean, guest};
		String[] command = new String[3];
		command[0]="python.exe";
		command[1]="C:/Users/user/PycharmProjects/IPS/main.py";
		command[2]=new_user;
		//String[] command = new String[] { "echo", "hello" };
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
