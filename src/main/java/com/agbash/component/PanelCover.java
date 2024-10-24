package com.agbash.component;

import com.agbash.swing.ButtonOutLine;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Mohmmed Mohammed
 */
public class PanelCover extends javax.swing.JPanel {

    private ActionListener event;
    private MigLayout layout;
    private JLabel title, description, description1;
    private ButtonOutLine button;
    private boolean isLogin;
    private final DecimalFormat df = new DecimalFormat("##0.###");

    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        init();
    }

    private void init() {
        title = new JLabel("Welcome Back");
        title.setFont(new Font("sansserif", 1, 30));
        title.setForeground(new Color(0, 0, 0));
        add(title);
        description = new JLabel("To Connect With Us...");
        //Font font = new Font("SansSerif", Font.PLAIN, 12);
        description.setForeground(new Color(0, 0, 0));
        description.setFont(new Font("sansserif", 1, 12));
        add(description);

        description1 = new JLabel("Use your Personal Info to log in");
        description1.setForeground(new Color(0, 0, 0));
        add(description1);

        button = new ButtonOutLine();
        button.setBackground(new Color(225, 225, 225));
        button.setForeground(new Color(0, 0, 0));
        button.setText("SIGN IN");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aEvent) {
                event.actionPerformed(aEvent);
            }
        });
        add(button, "w 60%, h 40");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics graphcs) {
        Graphics2D g2 = (Graphics2D) graphcs;
        GradientPaint gra = new GradientPaint(0, 0, new Color(3, 252, 206), 0, getHeight(), new Color(3, 252, 44));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(graphcs);
    }

    public void registerLeft(double reg) {
        reg = Double.valueOf(df.format(reg));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + reg + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + reg + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + reg + "% 0 0");
    }

    public void registerRight(double reg) {
        reg = Double.valueOf(df.format(reg));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + reg + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + reg + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + reg + "% 0 0");
    }

    public void loginLeft(double n) {

        n = Double.valueOf(df.format(n));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + n + "% 0 " + n + "%");
        layout.setComponentConstraints(description, "pad 0 " + n + "% 0 " + n + "%");
        layout.setComponentConstraints(description1, "pad 0 " + n + "% 0 " + n + "%");
    }

    public void loginRight(double n) {

        n = Double.valueOf(df.format(n));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + n + "% 0 " + n + "%");
        layout.setComponentConstraints(description, "pad 0 " + n + "% 0 " + n + "%");
        layout.setComponentConstraints(description1, "pad 0 " + n + "% 0 " + n + "%");
    }

    private void login(boolean login) {
        if (this.isLogin != login) {
            if (login) {
                title.setText("Hello...");
                description.setText("Enter your Personal Info");
                description1.setText("lets start your journy...");
                button.setText("SIGN UP");
            } else {
                title.setText("Welcome Back");
                description.setText("For keep connect with US.");
                description1.setText("Log in please");
                button.setText("SIGN IN");
            }
            this.isLogin = login;
        }
    }

    public void addEvent(ActionListener event) {
        this.event = event;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
