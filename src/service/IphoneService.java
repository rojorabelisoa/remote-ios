/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import iphone.MyUserInfo;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author rojo
 */
public class IphoneService {

    public static void getSmsDb() throws Exception {
        try{
            JSch jsch = new JSch();
            String host = JOptionPane.showInputDialog("Enter username@hostname", "root" + "@192.168.43.193");
            String user = host.substring(0, host.indexOf('@'));
            host = host.substring(host.indexOf('@') + 1);
            Session session = jsch.getSession(user, host, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            //session.setPassword("rojorabelisoa");
            // Maka ny Utilisateur sy mdp via UserInfo interface.
            UserInfo ui = new MyUserInfo();
            session.setUserInfo(ui);
            System.out.println("Etablissement Connection...");
            session.connect();
            System.out.println("Connection établie.");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.get("/var/mobile/Library/SMS/sms.db", "/Users/rojo/NetBeansProjects/iphone/sms.db");
            sftpChannel.get("/private/var/mobile/Library/AddressBook/AddressBook.sqlitedb", "/Users/rojo/NetBeansProjects/iphone/AddressBook.sqlitedb");
            sftpChannel.exit();
            session.disconnect();
        }
        catch(Exception e){
            throw e;
        }

    }

    public static void getSmsDb2() throws Exception {

        JSch jsch = new JSch();
        String host = JOptionPane.showInputDialog("Enter username@hostname", "root" + "@192.168.1.6");
        String user = host.substring(0, host.indexOf('@'));
        host = host.substring(host.indexOf('@') + 1);
        Session session = jsch.getSession(user, host, 22);
        // Maka ny Utilisateur sy mdp via UserInfo interface.
        UserInfo ui = new MyUserInfo();
        session.setUserInfo(ui);
        session.connect();
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.get("/var/mobile/Library/SMS/sms.db", "/Users/rojo/NetBeansProjects/iphone/sms.db");
        sftpChannel.exit();
        session.disconnect();

    }

    public static void sendSMS(String numero, String text) throws Exception {
        String command1 = "sendsms " + numero + " " + "\"" + text + "\"";
        JSch jsch = new JSch();
        String host = JOptionPane.showInputDialog("Enter username@hostname", "root" + "@192.168.1.2");
        String user = host.substring(0, host.indexOf('@'));
        host = host.substring(host.indexOf('@') + 1);
        Session session = jsch.getSession(user, host, 22);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword("rojorabelisoa");
        // Maka ny Utilisateur sy mdp via UserInfo interface.
        UserInfo ui = new MyUserInfo();
        session.setUserInfo(ui);
        System.out.println("Establishing Connection...");
        session.connect();
        System.out.println(" Connecter...");
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command1);
        InputStream in = channel.getInputStream();
        OutputStream out = channel.getOutputStream();
        ((ChannelExec) channel).setErrStream(System.err);
        ((ChannelExec) channel).setInputStream(System.in);
        channel.connect();
        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) {
                    break;
                }
                System.out.print(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                if (in.available() > 0) {
                    continue;
                }
                System.out.println("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
                throw ee;
            }
        }
        channel.disconnect();
        session.disconnect();

    }
}
