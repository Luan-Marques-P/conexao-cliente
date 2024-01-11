package com.luan.conectar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class ClienteSocketComInterface {
    private static ObjectOutputStream oos;
    private static Socket socket;
    private static ObjectInputStream ois;

    public static void main(String[] args) {
        String enderecoServidor = "192.168.8.188"; // Altere para o endereço do seu servidor
        int porta = 12345;

        try {
            socket = new Socket(enderecoServidor, porta);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            JFrame frame = new JFrame("Cliente - Compartilhamento de Tela e Mouse");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Dimension resolucao = new Dimension(1366, 768);

            JLabel screenLabel = new JLabel();
            screenLabel.setPreferredSize(resolucao);
            screenLabel.setHorizontalAlignment(SwingConstants.CENTER);
            screenLabel.setVerticalAlignment(SwingConstants.CENTER);
            screenLabel.setMaximumSize(resolucao);

            JScrollPane scrollPane = new JScrollPane(screenLabel);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setViewportView(screenLabel);
            scrollPane.setMaximumSize(resolucao);

            frame.setMaximumSize(resolucao);
            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

            frame.setSize(resolucao);
            frame.setResizable(false);
            frame.setVisible(true);

            screenLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Point posicao = e.getPoint();
                    enviarComandoMouseTeclado(posicao, SwingUtilities.isLeftMouseButton(e), SwingUtilities.isRightMouseButton(e));
                }
            });

            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    enviarComandoTeclado(e.getKeyChar(), true);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    enviarComandoTeclado(e.getKeyChar(), false);
                }
            });

            iniciarRecepcaoComandos(screenLabel, scrollPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarComandoMouseTeclado(Point point, boolean leftClick, boolean rightClick) {
        try {
            Comando comando = new ComandoMouseTeclado(point, leftClick, rightClick, '\0');
            oos.writeObject(comando);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarComandoTeclado(char keyChar, boolean keyPressed) {
        try {
            Comando comando = new ComandoTeclado(keyChar, keyPressed);
            oos.writeObject(comando);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void iniciarRecepcaoComandos(JLabel screenLabel, JScrollPane scrollPane) {
        new Thread(() -> {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        Comando comando = (Comando) ois.readObject();

                        if (comando.getTipo() == TipoComando.CAPTURA_TELA) {
                            byte[] imageBytes = (byte[]) comando.getDados();
                            ImageIcon imageIcon = new ImageIcon(imageBytes);
                            Image image = imageIcon.getImage().getScaledInstance(screenLabel.getWidth(), screenLabel.getHeight(), Image.SCALE_SMOOTH);

                            SwingUtilities.invokeLater(() -> {
                                screenLabel.setIcon(new ImageIcon(image));
                                screenLabel.repaint();
                                scrollPane.revalidate();
                            });
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 100); // Reduzi o intervalo para 100 milissegundos para uma atualização mais rápida
        }).start();
    }
}

