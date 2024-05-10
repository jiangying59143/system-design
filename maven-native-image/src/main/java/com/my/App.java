package com.my;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        System.setProperty("java.home", ".");
        // 创建 JFrame 实例
        JFrame frame = new JFrame("Button Example");

        // 创建 JButton 实例
        JButton button = new JButton("Click Me");

        // 设置按钮位置与大小
        button.setBounds(50, 50, 100, 40);

        // 为按钮添加事件处理
        button.addActionListener(e -> {
            // 点击按钮时弹出消息框
            JOptionPane.showMessageDialog(frame, "Button Clicked!");
        });

        // 将按钮添加到窗口中
        frame.add(button);

        // 设置窗口大小
        frame.setSize(300, 200);

        // 设置窗口关闭行为
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口可见
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
