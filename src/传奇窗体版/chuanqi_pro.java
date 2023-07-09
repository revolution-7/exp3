package 传奇窗体版;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;


import 传奇窗体版.*;
/*游戏说明：
这是一款使用GUI的可视化传奇小游戏，有三种职业可供选择，每种职业都有不同的属性优势，通过打怪、升级装备和升级人物来变强。
装备品质从低到高：白色（50%），绿色（25%），蓝色（15%），紫色（5%），金色（3.5%），传奇（1.5%）。高品质的装备一定优于低品质，除非你强化过。
普通怪兽掉落一件装备和一样材料，boss掉落双倍，金币也是boss掉落更多。
本游戏有自动装备（无装备时自动装备）和自动替换（新装备全部属性高于原有装备时自动替换）功能。
材料有五种：木材，强化石，圣水，万能石，洗炼石。并且有金币系统，强化需要1000金币，重铸2000金币。
强化需要前三种材料各两个，万能石可以代替前三种材料任意一个，洗炼石用于重铸装备，但是无法反悔，有风险，切记！
可以用金币购买材料，也可以卖出，但是记得留一些金币，因为无论强化还是重铸都需要金币。
在选择下一步行动界面有菜单栏，可以存档，也可以读档，默认保存到桌面，文件名默认是你的游戏名。
读档时先随便创一个角色，不影响读档的。

	最后，由于这是我个人闲暇时开发的小游戏，错误在所难免，希望各位能够指正。
	tel：19817100337；v:sqy17888；q：770426840。
	数创2201 苏启垚
 */
public class chuanqi_pro implements Serializable {
    hero h;
    monster m;
    equip e1,e2;
    boolean f_dizzy = false;
    boolean f_highattack = false;
    public void xuanjue() {
        JFrame frame = new JFrame("选职");
        JPanel panel = new JPanel();
        JLabel label = new JLabel("请选择您的职业");
        JButton b1 = new JButton("战士");
        JButton b2 = new JButton("法师");
        JButton b3 = new JButton("弓箭手");
        JTextField t1 = new JTextField();
        Font font = new Font("宋体", Font.PLAIN, 65);
        Font font2 = new Font("宋体", Font.PLAIN, 40);
        Font font3 = new Font("宋体", Font.PLAIN, 30);

        label.setFont(font);
        label.setHorizontalAlignment(JLabel.CENTER);
        b1.setFont(font2);
        b2.setFont(font2);
        b3.setFont(font2);
        t1.setFont(font3);

        t1.setText("请输入您的名字");
        t1.setHorizontalAlignment(JLabel.CENTER);

        panel.add(label);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(t1);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                h = new warrior();
                h.name=t1.getText();
                h.job="战士";
                frame.dispose();
                show_hero();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                h = new mage();
                h.name=t1.getText();
                frame.dispose();
                show_hero();
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                h = new archer();
                h.name=t1.getText();
                frame.dispose();
                show_hero();
            }
        });

        panel.setLayout(null);
        label.setBounds(100, 25, 800, 150);
        b1.setBounds(250, 200, 500, 125);
        b2.setBounds(250, 350, 500, 125);
        b3.setBounds(250, 500, 500, 125);
        t1.setBounds(100, 675,800, 125);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 50, 1000, 900);
        frame.setVisible(true);
    }
    public void show_hero() {
        JFrame frame = new JFrame("属性展示");
        JPanel panel = new JPanel();
        JButton b1 = new JButton("OK");
        JLabel label1 = new JLabel("名字："+h.name);
        JLabel label2 = new JLabel("职业："+h.job);
        JLabel label3 = new JLabel("攻击力："+h.attack);
        JLabel label4 = new JLabel("防御力："+h.defense);
        JLabel label5 = new JLabel("当前生命值："+h.life);
        JLabel label6 = new JLabel("最大生命值："+h.maxlife);
        JLabel label7 = new JLabel("法力值："+h.magic);
        JLabel label8 = new JLabel("当前等级："+h.level);
        JLabel label9 = new JLabel("当前经验："+h.exp_temp);
        JLabel label10 = new JLabel("尚缺经验："+Math.abs(h.exp-h.exp_temp));
        JLabel label11 =new JLabel("晕眩率："+(int)(h.skill[0]*100)+"%");
        JLabel label12= new JLabel("暴击率："+(int)(h.skill[1]*100)+"%");
        JLabel label13= new JLabel("暴击效果加成："+(int)(h.skill[2]*100)+"%");
        JLabel label14= new JLabel("破防加成："+(int)(h.skill[3]*100)+"%");
        JLabel label15= new JLabel("伤害加成："+(int)(h.skill[4]*100)+"%");
        JLabel label16= new JLabel("名刀触发概率："+(int)(h.skill[5]*100)+"%");
        JLabel label17= new JLabel("复活甲概率："+(int)(h.skill[6]*100)+"%");
        JLabel label18= new JLabel("永夜回复比例："+(int)(h.skill[7]*100)+"%,当生命不大于30%时恢复一定比例");
        JLabel label19= new JLabel("免伤比例："+(int)(h.skill[8]*100)+"%");
        JLabel label20= new JLabel("闪避率："+(int)(h.skill[9]*100)+"%");
        JLabel label21= new JLabel("战力值："+h.power);
        Font font = new Font("宋体", Font.PLAIN, 30);
        Font font2 = new Font("宋体", Font.PLAIN, 22);

        panel.add(b1);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);
        panel.add(label9);
        panel.add(label10);
        panel.add(label11);
        panel.add(label12);
        panel.add(label13);
        panel.add(label14);
        panel.add(label15);
        panel.add(label16);
        panel.add(label17);
        panel.add(label18);
        panel.add(label19);
        panel.add(label20);
        panel.add(label21);

        label1.setHorizontalAlignment(JLabel.CENTER);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label4.setHorizontalAlignment(JLabel.CENTER);
        label5.setHorizontalAlignment(JLabel.CENTER);
        label6.setHorizontalAlignment(JLabel.CENTER);
        label7.setHorizontalAlignment(JLabel.CENTER);
        label8.setHorizontalAlignment(JLabel.CENTER);
        label9.setHorizontalAlignment(JLabel.CENTER);
        label10.setHorizontalAlignment(JLabel.CENTER);
        label11.setHorizontalAlignment(JLabel.CENTER);
        label12.setHorizontalAlignment(JLabel.CENTER);
        label13.setHorizontalAlignment(JLabel.CENTER);
        label14.setHorizontalAlignment(JLabel.CENTER);
        label15.setHorizontalAlignment(JLabel.CENTER);
        label16.setHorizontalAlignment(JLabel.CENTER);
        label17.setHorizontalAlignment(JLabel.CENTER);
        label18.setHorizontalAlignment(JLabel.CENTER);
        label19.setHorizontalAlignment(JLabel.CENTER);
        label20.setHorizontalAlignment(JLabel.CENTER);
        label21.setHorizontalAlignment(JLabel.CENTER);

        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);
        label7.setFont(font);
        label8.setFont(font);
        label9.setFont(font);
        label10.setFont(font);
        label11.setFont(font);
        label12.setFont(font);
        label13.setFont(font);
        label14.setFont(font);
        label15.setFont(font);
        label16.setFont(font);
        label17.setFont(font);
        label18.setFont(font2);
        label19.setFont(font);
        label20.setFont(font);
        label21.setFont(font);
        b1.setFont(font);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                choice();
            }
        });

        panel.setLayout(null);
        label1.setBounds(100, 25, 300, 50);
        label2.setBounds(100, 100, 300, 50);
        label3.setBounds(100, 175, 300, 50);
        label4.setBounds(100, 250, 300, 50);
        label5.setBounds(100, 325,300, 50);
        label6.setBounds(100, 400, 300, 50);
        label7.setBounds(100, 475, 300, 50);
        label8.setBounds(100, 550, 300, 50);
        label9.setBounds(100, 625, 300, 50);
        label10.setBounds(100, 700,300, 50);
        label11.setBounds(100, 775, 300, 50);
        label12.setBounds(600, 25, 300, 50);
        label13.setBounds(600, 100, 300, 50);
        label14.setBounds(600, 175, 300, 50);
        label15.setBounds(600, 250,300, 50);
        label16.setBounds(600, 325, 300, 50);
        label17.setBounds(600, 400, 300, 50);
        label18.setBounds(450, 475, 550, 50);
        label19.setBounds(600, 550, 300, 50);
        label20.setBounds(600, 625,300, 50);
        label21.setBounds(600,700, 300, 50);
        b1.setBounds(600, 775, 325, 65);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 50, 1000,900);
        frame.setVisible(true);
    }
    public void choice() {
        cal_defense();
        JFrame frame = new JFrame("您现在要做...");
        JPanel panel = new JPanel();
        JLabel label = new JLabel("请选择您的下一步行动");
        JButton b1 = new JButton("打怪");
        JButton b2 = new JButton("升级装备");
        JButton b3 = new JButton("买卖材料");
        JButton b4 = new JButton("展示属性");
        JMenuBar m_total = new JMenuBar();
        JMenu m = new JMenu("文件");
        JMenuItem m1 = new JMenuItem("保存");
        JMenuItem m2 = new JMenuItem("打开");
        frame.setJMenuBar(m_total);
        m_total.add(m);
        m.add(m1);
        m.add(m2);

        m1.addActionListener(new ActionListener() {//保存
            public void actionPerformed(ActionEvent e) {
                // 创建一个文件选择器
                JFileChooser fileChooser = new JFileChooser();
                // 设置文件选择器的标题
                fileChooser.setDialogTitle("游戏存档");
                // 获取桌面路径
                String desktopPath = System.getProperty("user.home") + "/Desktop";
                // 设置文件选择器的默认路径为桌面
                fileChooser.setCurrentDirectory(new File(desktopPath));
                // 设置文件选择器的默认文件名
                fileChooser.setSelectedFile(new File(h.name+".ser"));
                // 显示文件选择器，并获取用户的选择结果
                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // 获取用户选择的文件
                    File file = fileChooser.getSelectedFile();
                    // 获取用户选择的文件名
                    String fileName = file.getName();
                    // 获取用户选择的文件路径
                    String filePath = file.getPath();
                    // 打印用户选择的文件名和路径
                    System.out.println("文件名：" + fileName);
                    System.out.println("文件路径：" + filePath);
                    // 以下是保存文件的逻辑，你可以根据你的需求来实现
                    try {
                        FileOutputStream f1 = new FileOutputStream(filePath);
                        ObjectOutputStream oos = new ObjectOutputStream(f1);
                        oos.writeObject(h);
                        oos.close();
                        f1.close();
                        tips("保存成功",frame);
                    }catch(Exception ee){
                        tips("保存失败",frame);
                        ee.printStackTrace();
                    }
                }
            }
        });
        m2.addActionListener(new ActionListener() {//打开
            public void actionPerformed(ActionEvent e) {
                // 创建一个文件选择器
                JFileChooser fileChooser = new JFileChooser();
                // 设置文件选择器的标题
                fileChooser.setDialogTitle("游戏读档");
                // 获取桌面路径
                String desktopPath = System.getProperty("user.home") + "/Desktop";
                // 设置文件选择器的默认路径为桌面
                fileChooser.setCurrentDirectory(new File(desktopPath));
                // 设置文件选择器的默认文件名
                fileChooser.setSelectedFile(new File(h.name+".ser"));
                // 显示文件选择器，并获取用户的选择结果
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // 获取用户选择的文件
                    File file = fileChooser.getSelectedFile();
                    // 获取用户选择的文件名
                    String fileName = file.getName();
                    // 获取用户选择的文件路径
                    String filePath = file.getPath();
                    // 打印用户选择的文件名和路径
                    System.out.println("文件名：" + fileName);
                    System.out.println("文件路径：" + filePath);
                    // 以下是读取文件的逻辑，你可以根据你的需求来实现
                    try {
                        FileInputStream f1 = new FileInputStream(filePath);
                        ObjectInputStream ois = new ObjectInputStream(f1);
                        h = (hero)ois.readObject();
                        ois.close();
                        f1.close();
                        tips("读取成功",frame);
                    }catch(Exception ee){
                        tips("读取失败",frame);
                        ee.printStackTrace();
                    }
                }
            }
        });

        Font font = new Font("宋体", Font.PLAIN, 65);
        Font font2 = new Font("宋体", Font.PLAIN, 40);
        Font font3 = new Font("宋体", Font.PLAIN, 20);

        label.setFont(font);
        label.setHorizontalAlignment(JLabel.CENTER);
        b1.setFont(font2);
        b2.setFont(font2);
        b3.setFont(font2);
        b4.setFont(font2);
        m.setFont(font3);
        m1.setFont(font3);
        m2.setFont(font3);

        panel.add(label);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);

        b1.addActionListener(new ActionListener() {//打怪
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                fight();
            }
        });
        b2.addActionListener(new ActionListener() {//升级装备
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                up_equiplevel();
            }
        });
        b3.addActionListener(new ActionListener() {//购买材料
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                buy();
            }
        });
        b4.addActionListener(new ActionListener() {//展示属性
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                show_hero();
            }
        });
        panel.setLayout(null);
        label.setBounds(100, 25, 800, 150);
        b1.setBounds(250, 170, 500, 120);
        b2.setBounds(250, 345, 500, 120);
        b3.setBounds(250, 520, 500, 120);
        b4.setBounds(250, 695, 500, 120);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 50, 1000,900);
        frame.setVisible(true);
    }
    public void buy() {
        JFrame frame = new JFrame("有钱任性~");
        JPanel p_left =new JPanel();
        JPanel p_right =new JPanel();
        JPanel p = new JPanel();
        JTextArea text = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(text,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        text.setLineWrap(true);//自动换行
        text.setWrapStyleWord(true);//单词换行
        text.setEditable(false);//设置文本区域为只读

        JButton b1= new JButton("买入");
        JButton b2= new JButton("卖出(九折)");
        JButton b3= new JButton("返回");
        JRadioButton rb1 = new JRadioButton("木材,金币*1000", false);
        JRadioButton rb2 = new JRadioButton("强化石,金币*1000", false);
        JRadioButton rb3 = new JRadioButton("圣水,金币*1000", false);
        JRadioButton rb4 = new JRadioButton("万能石,金币*2000", false);
        JRadioButton rb5 = new JRadioButton("洗炼石,金币*2000", false);
        ButtonGroup bg =new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        bg.add(rb4);
        bg.add(rb5);

        Font font = new Font("宋体", Font.PLAIN, 30);
        rb1.setFont(font);
        rb2.setFont(font);
        rb3.setFont(font);
        rb4.setFont(font);
        rb5.setFont(font);
        text.setFont(font);
        b1.setFont(font);
        b2.setFont(font);
        b3.setFont(font);

        b1.addActionListener(new ActionListener() {//买入
            public void actionPerformed(ActionEvent e) {
                if (rb1.isSelected()) {
                    if(h.money>=1000) {
                        h.money-=1000;
                        h.ma.add(new wood());
                        h.cal_material();
                    }else {
                        tips("金币不足",frame);
                    }
                }else if (rb2.isSelected()) {
                    if(h.money>=1000) {
                        h.money-=1000;
                        h.ma.add(new stone());
                        h.cal_material();
                    }else {
                        tips("金币不足",frame);
                    }
                }else if (rb3.isSelected()) {
                    if(h.money>=1000) {
                        h.money-=1000;
                        h.ma.add(new water());
                        h.cal_material();
                    }else {
                        tips("金币不足",frame);
                    }
                }else if (rb4.isSelected()) {
                    if(h.money>=2000) {
                        h.money-=2000;
                        h.ma.add(new stones_pro());
                        h.cal_material();
                    }else {
                        tips("金币不足",frame);
                    }
                }else if (rb5.isSelected()) {
                    if(h.money>=2000) {
                        h.money-=2000;
                        h.ma.add(new stones_recreat());
                        h.cal_material();
                    }else {
                        tips("金币不足",frame);
                    }
                }
                init(text);
            }
        });
        b2.addActionListener(new ActionListener() {//卖出
            public void actionPerformed(ActionEvent e) {
                if (rb1.isSelected()) {
                    if(h.ma_num[0]>0) {
                        h.ma_num[0]--;
                        h.money+=1800;
                    }else {
                        tips("木材不足",frame);
                    }
                }else if (rb2.isSelected()) {
                    if(h.ma_num[1]>0) {
                        h.ma_num[1]--;
                        h.money+=1800;
                    }else {
                        tips("强化石不足",frame);
                    }
                }else if (rb3.isSelected()) {
                    if(h.ma_num[2]>0) {
                        h.ma_num[2]--;
                        h.money+=1800;
                    }else {
                        tips("圣水不足",frame);
                    }
                }else if (rb4.isSelected()) {
                    if(h.ma_num[3]>0) {
                        h.ma_num[3]--;
                        h.money+=1800;
                    }else {
                        tips("万能石不足",frame);
                    }
                }else if (rb5.isSelected()) {
                    if(h.ma_num[4]>0) {
                        h.ma_num[4]--;
                        h.money+=1800;
                    }else {
                        tips("洗炼石不足",frame);
                    }
                }
                init(text);
            }
        });
        b3.addActionListener(new ActionListener() {//返回
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                choice();
            }
        });

        init(text);

        p_left.setLayout(new BoxLayout(p_left,BoxLayout.Y_AXIS));
        p_right.setLayout(new BoxLayout(p_right,BoxLayout.Y_AXIS));
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));

        p_left.add(Box.createVerticalStrut(100));
        p_left.add(rb1);
        p_left.add(Box.createVerticalStrut(100));
        p_left.add(rb2);
        p_left.add(Box.createVerticalStrut(100));
        p_left.add(rb3);
        p_left.add(Box.createVerticalStrut(100));
        p_left.add(rb4);
        p_left.add(Box.createVerticalStrut(100));
        p_left.add(rb5);
        p_left.add(Box.createVerticalStrut(100));
        p_right.add(scrollPane);
        p.add(b1);
        p.add(Box.createHorizontalStrut(100));
        p.add(b2);
        p.add(Box.createHorizontalStrut(100));
        p.add(b3);
        p_right.add(p);

        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.X_AXIS));
        frame.getContentPane().add(p_left);
        frame.getContentPane().add(p_right);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 50, 1000,900);
        frame.setVisible(true);
    }
    public void fight() {
        JFrame frame = new JFrame("献出心脏吧！！！塔塔开！！！");
        JPanel panel_text = new JPanel();
        JTextArea text = new JTextArea();
        JButton b1 = new JButton("攻击");
        JButton b2 = new JButton("魔法攻击");
        JButton b3 = new JButton("防御");
        JButton b4 = new JButton("恢复");
        JButton b5 = new JButton("逃跑（丢失九成金币！）");
        Font font = new Font("宋体", Font.PLAIN, 20);
        Font font2 = new Font("宋体", Font.PLAIN, 17);
        JScrollPane scrollPane = new JScrollPane(text,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        m_random();
        Mypanel_jpg panel_jpg =new Mypanel_jpg(h,m);

        panel_text.setLayout(null);
        panel_text.setBounds(0, 600, 1000, 300);
        scrollPane.setBounds(75, 640, 850, 210);
        text.setLineWrap(true);//自动换行
        text.setWrapStyleWord(true);//单词换行
        text.setEditable(false);//设置文本区域为只读
        text.setText("您遇到了"+m.name+System.lineSeparator());
        text.append(System.lineSeparator());
        h.show(text);
        m.show(text);
        text.setCaretPosition(0);

        b1.setBounds(25, 560, 150, 75);
        b2.setBounds(200, 560, 150, 75);
        b3.setBounds(375, 560, 150, 75);
        b4.setBounds(550, 560, 150, 75);
        b5.setBounds(725, 560, 225, 75);

        text.setFont(font);
        b1.setFont(font2);
        b2.setFont(font2);
        b3.setFont(font2);
        b4.setFont(font2);
        b5.setFont(font2);

        b1.addActionListener(new ActionListener() {//攻击
            public void actionPerformed(ActionEvent e) {
                if(h.eq[1]!=null) {
                    f_highattack=h.eq[1].highattack(h);
                    if(f_highattack==true) {
                        h.highattack(m);
                        text.append("Lucky!暴击了，暴击效果："+(int)((2+h.skill[2])*100)+"%"
                                +System.lineSeparator());
                        text.append(System.lineSeparator());
                    }else {
                        h.attack(m);
                        text.append("您发动了进攻"
                                +System.lineSeparator());
                        text.append(System.lineSeparator());
                    }
                }else {
                    h.attack(m);
                    text.append("您发动了进攻"
                            +System.lineSeparator());
                    text.append(System.lineSeparator());
                }
                if(h.eq[0]!=null) {
                    f_dizzy=h.eq[0].dizzy(h);
                    if(f_dizzy==true) {
                        text.append("Lucky!造成晕眩，怪物本回合无法行动"
                                +System.lineSeparator());
                        text.append(System.lineSeparator());
                    }
                }
                if(m.life<=0) {
                    frame.dispose();
                    prize();
                }else {
                    m.show(text);
                    m_action(text,frame);
                }
            }
        });
        b2.addActionListener(new ActionListener() {//魔法攻击
            public void actionPerformed(ActionEvent e){
                if (h.eq[1] != null) {
                    f_highattack = h.eq[1].highattack(h);
                    if (f_highattack==true) { // 如果产生了暴击
                        h.highmagic(m);
                        text.append("Lucky! 暴击了，暴击效果：" + (int)((2+h.skill[2])*100) + "%"
                                + System.lineSeparator());
                        text.append(System.lineSeparator()); // 添加一个空行
                    } else {
                        h.magic(m);
                        text.append("您发动了魔法进攻" + System.lineSeparator());
                        text.append(System.lineSeparator()); // 添加一个空行
                    }
                } else {
                    h.magic(m);
                    text.append("您发动了魔法进攻" + System.lineSeparator());
                    text.append(System.lineSeparator()); // 添加一个空行
                }
                // 处理装备的晕眩效果
                if (h.eq[0] != null) {
                    f_dizzy = h.eq[0].dizzy(h);
                    if (f_dizzy==true) {
                        text.append("Lucky! 造成晕眩，怪物本回合无法行动" + System.lineSeparator());
                        text.append(System.lineSeparator()); // 添加一个空行
                    }
                }
                if(m.life<=0) {
                    frame.dispose();
                    prize();
                }else {
                    m.show(text);
                    m_action(text,frame);
                }
            }
        });
        b3.addActionListener(new ActionListener() {//防御
            public void actionPerformed(ActionEvent e) {
                h.defense();
                text.append("您进行了防御" + System.lineSeparator());
                text.append(System.lineSeparator()); // 添加一个空行
                h.show(text);
                m_action(text,frame);
            }
        });
        b4.addActionListener(new ActionListener() {//恢复
            public void actionPerformed(ActionEvent e) {
                h.life();
                text.append("您恢复了生命" + System.lineSeparator());
                text.append(System.lineSeparator()); // 添加一个空行
                h.show(text);
                m_action(text,frame);
            }
        });
        b5.addActionListener(new ActionListener() {//逃跑
            public void actionPerformed(ActionEvent e) {
                h.escape();
                text.append("您逃跑了" + System.lineSeparator());
                text.append(System.lineSeparator()); // 添加一个空行
                frame.dispose();
                choice();
            }
        });

        panel_text.add(b1);
        panel_text.add(b2);
        panel_text.add(b3);
        panel_text.add(b4);
        panel_text.add(b5);
        panel_text.add(scrollPane);

        frame.getContentPane().add(panel_jpg,BorderLayout.CENTER);
        frame.getContentPane().add(panel_text);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 50, 1000,900);
        frame.setVisible(true);
    }
    public void cal_defense() {
        if(h instanceof warrior) {
            h.defense=15+(h.level-1)*5;
        }else if(h instanceof mage) {
            h.defense=3+(h.level-1)*5;
        }else {
            h.defense=6+(h.level-1)*5;
        }
        for(int i=0;i<7;i++) {
            if(h.eq[i]!=null) {
                h.defense+=h.eq[i].defense;
            }
        }
    }
    public void prize() {
        JFrame frame = new JFrame("O泡时间到");
        JPanel panel = new JPanel();
        JTextArea text1 = new JTextArea();
        JTextArea text2 = new JTextArea();
        JTextArea text3 = new JTextArea();
        text2.setEditable(false);
        text3.setEditable(false);
        JLabel label1 = new JLabel("原装备属性:"+"\n");
        JLabel label2 = new JLabel("新装备属性:"+"\n");
        JButton b1 = new JButton("替换");
        JButton b2 = new JButton("不替换");
        JButton b3 = new JButton("OK");
        JScrollPane scrollPane = new JScrollPane(text1,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        Font font = new Font("宋体", Font.PLAIN, 23);
        Font font2 = new Font("宋体", Font.PLAIN, 30);

        equip[] eq1 =new equip[2];
        prize(text1);
        e1 = m.drop(text1);
        eq1[0]=e1;
        e1.show(text1);
        e1.show(text3);
        if(h.eq[e1.id]==null) {
            h.original_equipment(e1, text1,text3, label1, label2,0);
            if(m.boss==1) {
                m.boss=0;
                e2=m.drop(text1);
                e2.show(text1);
                eq1[0]=e2;
                text2.setText("");
                text3.setText("");
                e2.show(text3);
                if(h.eq[e2.id]==null) {
                    h.original_equipment(e2, text1, text3,label1, label2,0);
                    h.show(text1);
                }else {
                    h.eq[e2.id].show(text2);
                    h.original_equipment(e2, text1,text3, label1, label2,0);
                }
            }
        }else {
            text2.setText("");
            h.eq[e1.id].show(text2);
            h.original_equipment(e1, text1,text3, label1, label2,0);
        }
        text1.setCaretPosition(0);//光标置于顶部

        text1.setFont(font);
        text2.setFont(font);
        text3.setFont(font);
        label1.setFont(font);
        label2.setFont(font);
        b1.setFont(font2);
        b2.setFont(font2);
        b3.setFont(font2);

        panel.setLayout(null);
        label1.setBounds(530,0,200, 100);
        label2.setBounds(750, 0, 200, 100);
        b1.setBounds(150, 660, 200, 100);
        b2.setBounds(650, 660, 200, 100);
        b3.setBounds(400, 660, 200, 100);

        scrollPane.setBounds(10, 10, 500, 620);
        text2.setBounds(530,75, 200, 555);
        text3.setBounds(750, 75, 200, 555);

        text1.setLineWrap(true);//自动换行
        text1.setWrapStyleWord(true);//单词换行
        text1.setEditable(false);//设置文本区域为只读
        text2.setLineWrap(true);//自动换行
        text3.setLineWrap(true);//自动换行

        b1.addActionListener(new ActionListener() {//替换
            public void actionPerformed(ActionEvent e) {
                h.original_equipment(eq1[0], text1,text3, label1, label2,1);
                h.show(text1);
                if(m.boss==1) {
                    m.boss=0;
                    e2=m.drop(text1);
                    e2.show(text1);
                    eq1[0]=e2;
                    text2.setText("");
                    text3.setText("");
                    e2.show(text3);
                    if(h.eq[e2.id]==null) {
                        h.original_equipment(e2, text1,text3, label1, label2,0);
                        h.show(text1);
                    }else {
                        h.eq[e2.id].show(text2);
                        h.original_equipment(e2, text1,text3, label1, label2,0);
                    }
                }
            }
        });
        b2.addActionListener(new ActionListener() {//不替换
            public void actionPerformed(ActionEvent e) {
                h.original_equipment(eq1[0], text1,text3, label1, label2,0);
                h.show(text1);
                if(m.boss==1) {
                    m.boss=0;
                    e2=m.drop(text1);
                    e2.show(text1);
                    eq1[0]=e2;
                    text2.setText("");
                    text3.setText("");
                    e2.show(text3);
                    if(h.eq[e2.id]==null) {
                        h.original_equipment(e2, text1,text3, label1, label2,0);
                        h.show(text1);
                    }else {
                        h.eq[e2.id].show(text2);
                        h.original_equipment(e2, text1,text3, label1, label2,0);
                    }
                }
            }
        });
        b3.addActionListener(new ActionListener() {//ok
            public void actionPerformed(ActionEvent e) {
                if(m.boss==0) {
                    frame.dispose();
                    choice();
                }else {
                    tips("请选择替换/不替换先~",frame);
                }
            }
        });

        panel.add(label1);
        panel.add(label2);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(scrollPane);
        panel.add(text2);
        panel.add(text3);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 50, 1000,900);
        frame.setVisible(true);
    }
    public void tips(String s,JFrame jf) {
        JFrame frame = new JFrame("提示!");
        JLabel label1 = new JLabel(s,JLabel.CENTER);
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton b1 = new JButton("关闭(仅关闭提示页面)");
        JButton b2 = new JButton("返回选择页面(会关闭所有页面)");
        Font f1 = new Font("宋体", Font.PLAIN, 20);
        Font f2 = new Font("宋体", Font.PLAIN, 15);
        label1.setFont(f1);
        b1.setFont(f2);
        b2.setFont(f2);
        p1.add(b2);
        p1.add(b1);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                jf.dispose();
                choice();
            }
        });

        frame.getContentPane().add(label1,BorderLayout.CENTER);
        frame.getContentPane().add(p1,BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(700,400, 650,200);
        frame.setVisible(true);
    }
    public void up_equiplevel() {
        JFrame frame =new JFrame("浅升级一下吧~");
        Font font = new Font("宋体", Font.PLAIN, 30);
        JTextArea material = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(material,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel p_left = new JPanel();
        JPanel p_right = new JPanel();
        JPanel p_1 = new JPanel();
        JPanel p_2 = new JPanel();
        JPanel p_3 = new JPanel();
        JPanel p_4 = new JPanel();
        JPanel p_5= new JPanel();
        JPanel p_6 = new JPanel();
        JPanel p_7= new JPanel();
        p_1.setLayout(new BoxLayout(p_1,BoxLayout.X_AXIS));
        p_2.setLayout(new BoxLayout(p_2,BoxLayout.X_AXIS));
        p_3.setLayout(new BoxLayout(p_3,BoxLayout.X_AXIS));
        p_4.setLayout(new BoxLayout(p_4,BoxLayout.X_AXIS));
        p_5.setLayout(new BoxLayout(p_5,BoxLayout.X_AXIS));
        p_6.setLayout(new BoxLayout(p_6,BoxLayout.X_AXIS));
        p_7.setLayout(new BoxLayout(p_7,BoxLayout.X_AXIS));
        p_left.setLayout(new BoxLayout(p_left,BoxLayout.Y_AXIS));
        p_right.setLayout(new BoxLayout(p_right,BoxLayout.Y_AXIS));

        JRadioButton rb1 = new JRadioButton("帽子", false);
        JRadioButton rb2 = new JRadioButton("武器", false);
        JRadioButton rb3 = new JRadioButton("衣服", false);
        JRadioButton rb4 = new JRadioButton("裤子", false);
        JRadioButton rb5 = new JRadioButton("鞋子", false);
        JRadioButton rb6 = new JRadioButton("项链", false);
        JRadioButton rb7 = new JRadioButton("戒指", false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        bg.add(rb4);
        bg.add(rb5);
        bg.add(rb6);
        bg.add(rb7);
        JTextArea text1 =new JTextArea();
        JTextArea text2 =new JTextArea();
        JTextArea text3 =new JTextArea();
        JTextArea text4 =new JTextArea();
        JTextArea text5 =new JTextArea();
        JTextArea text6 =new JTextArea();
        JTextArea text7 =new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(text1,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scrollPane2 = new JScrollPane(text2,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scrollPane3 = new JScrollPane(text3,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scrollPane4 = new JScrollPane(text4,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scrollPane5 = new JScrollPane(text5,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scrollPane6 = new JScrollPane(text6,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scrollPane7 = new JScrollPane(text7,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        text1.setLineWrap(true);//自动换行
        text1.setWrapStyleWord(true);//单词换行
        text1.setEditable(false);//设置文本区域为只读
        text2.setLineWrap(true);//自动换行
        text2.setWrapStyleWord(true);//单词换行
        text2.setEditable(false);//设置文本区域为只读
        text3.setLineWrap(true);//自动换行
        text3.setWrapStyleWord(true);//单词换行
        text3.setEditable(false);//设置文本区域为只读
        text4.setLineWrap(true);//自动换行
        text4.setWrapStyleWord(true);//单词换行
        text4.setEditable(false);//设置文本区域为只读
        text5.setLineWrap(true);//自动换行
        text5.setWrapStyleWord(true);//单词换行
        text5.setEditable(false);//设置文本区域为只读
        text6.setLineWrap(true);//自动换行
        text6.setWrapStyleWord(true);//单词换行
        text6.setEditable(false);//设置文本区域为只读
        text7.setLineWrap(true);//自动换行
        text7.setWrapStyleWord(true);//单词换行
        text7.setEditable(false);//设置文本区域为只读
        material.setLineWrap(true);//自动换行
        material.setWrapStyleWord(true);//单词换行
        material.setEditable(false);//设置文本区域为只读

        JButton b1 = new JButton("强化(金币*1000)");
        JButton b2 = new JButton("重铸(金币*2000)(有风险！)");
        JButton b3 = new JButton("返回");

        b1.addActionListener(new ActionListener() {//强化
            public void actionPerformed(ActionEvent e) {
                if (rb1.isSelected()) {
                    // 帽子按钮被选中
                    // 进行相关操作
                    if(h.eq[0]!=null) {
                        h.up_equip(0,frame,chuanqi_pro.this);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb2.isSelected()) {
                    // 武器按钮被选中
                    // 进行相关操作
                    if(h.eq[1]!=null) {
                        h.up_equip(1,frame,chuanqi_pro.this);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb3.isSelected()) {
                    // 衣服按钮被选中
                    // 进行相关操作
                    if(h.eq[2]!=null) {
                        h.up_equip(2,frame,chuanqi_pro.this);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb4.isSelected()) {
                    // 裤子按钮被选中
                    // 进行相关操作
                    if(h.eq[3]!=null) {
                        h.up_equip(3,frame,chuanqi_pro.this);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb5.isSelected()) {
                    // 鞋子按钮被选中
                    // 进行相关操作
                    if(h.eq[4]!=null) {
                        h.up_equip(4,frame,chuanqi_pro.this);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb6.isSelected()) {
                    // 项链按钮被选中
                    // 进行相关操作
                    if(h.eq[5]!=null) {
                        h.up_equip(5,frame,chuanqi_pro.this);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb7.isSelected()) {
                    // 戒指按钮被选中
                    // 进行相关操作
                    if(h.eq[6]!=null) {
                        h.up_equip(6,frame,chuanqi_pro.this);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else {
                    // 没有任何按钮被选中
                    tips("亲，请选一件装备先~",frame);
                }
                init(text1,text2,text3,text4,text5,text6,text7,material);
            }
        });
        b2.addActionListener(new ActionListener() {//重铸
            public void actionPerformed(ActionEvent e) {
                if (rb1.isSelected()) {
                    // 帽子按钮被选中
                    // 进行相关操作
                    if(h.eq[0]!=null) {
                        h.eq[0]=h.eq[0].recast(h,chuanqi_pro.this,frame);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb2.isSelected()) {
                    // 武器按钮被选中
                    // 进行相关操作
                    if(h.eq[1]!=null) {
                        h.eq[1]=h.eq[1].recast(h,chuanqi_pro.this,frame);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb3.isSelected()) {
                    // 衣服按钮被选中
                    // 进行相关操作
                    if(h.eq[2]!=null) {
                        h.eq[2]=h.eq[2].recast(h,chuanqi_pro.this,frame);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb4.isSelected()) {
                    // 裤子按钮被选中
                    // 进行相关操作
                    if(h.eq[3]!=null) {
                        h.eq[3]=h.eq[3].recast(h,chuanqi_pro.this,frame);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb5.isSelected()) {
                    // 鞋子按钮被选中
                    // 进行相关操作
                    if(h.eq[4]!=null) {
                        h.eq[4]=h.eq[4].recast(h,chuanqi_pro.this,frame);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb6.isSelected()) {
                    // 项链按钮被选中
                    // 进行相关操作
                    if(h.eq[5]!=null) {
                        h.eq[5]=h.eq[5].recast(h,chuanqi_pro.this,frame);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else if (rb7.isSelected()) {
                    // 戒指按钮被选中
                    // 进行相关操作
                    if(h.eq[6]!=null) {
                        h.eq[6]=h.eq[6].recast(h,chuanqi_pro.this,frame);
                    }else {
                        tips("请选择拥有的装备",frame);
                    }
                } else {
                    // 没有任何按钮被选中
                    tips("亲，请选一件装备先~",frame);
                }
                init(text1,text2,text3,text4,text5,text6,text7,material);
            }
        });
        b3.addActionListener(new ActionListener() {//返回
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                choice();
            }
        });

        rb1.setFont(font);
        rb2.setFont(font);
        rb3.setFont(font);
        rb4.setFont(font);
        rb5.setFont(font);
        rb6.setFont(font);
        rb7.setFont(font);
        b1.setFont(font);
        b2.setFont(font);
        b3.setFont(font);
        material.setFont(font);

        init(text1,text2,text3,text4,text5,text6,text7,material);

        p_1.add(rb1);
        p_1.add(scrollPane1);
        p_2.add(rb2);
        p_2.add(scrollPane2);
        p_3.add(rb3);
        p_3.add(scrollPane3);
        p_4.add(rb4);
        p_4.add(scrollPane4);
        p_5.add(rb5);
        p_5.add(scrollPane5);
        p_6.add(rb6);
        p_6.add(scrollPane6);
        p_7.add(rb7);
        p_7.add(scrollPane7);
        p_left.add(p_1);
        p_left.add(Box.createVerticalStrut(10));
        p_left.add(p_2);
        p_left.add(Box.createVerticalStrut(10));
        p_left.add(p_3);
        p_left.add(Box.createVerticalStrut(10));
        p_left.add(p_4);
        p_left.add(Box.createVerticalStrut(10));
        p_left.add(p_5);
        p_left.add(Box.createVerticalStrut(10));
        p_left.add(p_6);
        p_left.add(Box.createVerticalStrut(10));
        p_left.add(p_7);
        p_right.add(Box.createVerticalStrut(75));
        p_right.add(scrollPane);
        p_right.add(Box.createVerticalStrut(50));
        p_right.add(Box.createHorizontalStrut(100));
        p_right.add(b1);
        p_right.add(Box.createVerticalStrut(100));
        p_right.add(b2);
        p_right.add(Box.createVerticalStrut(100));
        p_right.add(b3);
        p_right.add(Box.createVerticalStrut(50));

        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.X_AXIS));
        frame.getContentPane().add(p_left);
        frame.add(Box.createHorizontalStrut(10));
        frame.getContentPane().add(p_right);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 50, 1000,900);
        frame.setVisible(true);
    }
    public void init(JTextArea t) {
        t.setText("木材:"+h.ma_num[0]+"个"+System.lineSeparator()+
                "强化石:"+h.ma_num[1]+"个"+System.lineSeparator()+
                "圣水:"+h.ma_num[2]+"个"+System.lineSeparator()+
                "万能石:"+h.ma_num[3]+"个"+System.lineSeparator()+
                "洗炼石:"+h.ma_num[4]+"个"+System.lineSeparator()+
                "金币:"+h.money+"个"+System.lineSeparator());
    }
    public void init(JTextArea t1,JTextArea t2,JTextArea t3,JTextArea t4,JTextArea t5,JTextArea t6,JTextArea t7,JTextArea tt) {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
        tt.setText("");

        Font f1 = new Font("宋体", Font.PLAIN, 22);
        Font f2 = new Font("宋体", Font.PLAIN, 30);
        t1.setFont(f1);
        t2.setFont(f1);
        t3.setFont(f1);
        t4.setFont(f1);
        t5.setFont(f1);
        t6.setFont(f1);
        t7.setFont(f1);
        tt.setFont(f2);
        tt.setText("木材:"+h.ma_num[0]+"个"+System.lineSeparator()+
                "强化石:"+h.ma_num[1]+"个"+System.lineSeparator()+
                "圣水:"+h.ma_num[2]+"个"+System.lineSeparator()+
                "万能石:"+h.ma_num[3]+"个"+System.lineSeparator()+
                "洗炼石:"+h.ma_num[4]+"个"+System.lineSeparator()+
                "金币:"+h.money+"个"+System.lineSeparator());
        if(h.eq[0]!=null) {
            h.eq[0].show(t1);
        }else {
            t1.setText("无");
        }
        if(h.eq[1]!=null) {
            h.eq[1].show(t2);
        }else {
            t2.setText("无");
        }
        if(h.eq[2]!=null) {
            h.eq[2].show(t3);
        }else {
            t3.setText("无");
        }
        if(h.eq[3]!=null) {
            h.eq[3].show(t4);
        }else {
            t4.setText("无");
        }
        if(h.eq[4]!=null) {
            h.eq[4].show(t5);
        }else {
            t5.setText("无");
        }
        if(h.eq[5]!=null) {
            h.eq[5].show(t6);
        }else {
            t6.setText("无");
        }
        if(h.eq[6]!=null) {
            h.eq[6].show(t7);
        }else {
            t7.setText("无");
        }
        t1.setCaretPosition(0);
        t2.setCaretPosition(0);
        t3.setCaretPosition(0);
        t4.setCaretPosition(0);
        t5.setCaretPosition(0);
        t6.setCaretPosition(0);
        t7.setCaretPosition(0);
        tt.setCaretPosition(0);
    }
    public void m_random() {
        double x =Math.random()*2;
        if(x<=1.5) {
            int i =(int)(Math.random()*4);
            if (i == 0) {
                m = new goblins(h);
            } else if (i == 1) {
                m = new witch(h);
            } else if (i == 2) {
                m = new clown(h);
            } else {
                m = new rampage(h);
            }
        }else {
            int i =(int)(Math.random()*4);
            if (i == 0) {
                m = new bossgoblins(h);
            } else if (i == 1) {
                m = new bosswitch(h);
            } else if (i == 2) {
                m = new bossclown(h);
            } else {
                m = new bossrampage(h);
            }
        }
    }
    public void prize(JTextArea text) {
        if(m.life<=0) {//杀死怪物后，属性调整
            if(m.boss==0) {
                text.append("您消灭了怪物"
                        +System.lineSeparator());
                text.append(System.lineSeparator());
                material material_temp =m.generate();
                text.append("您获得了"+material_temp.name
                        +System.lineSeparator());
                h.ma.add(material_temp);
                h.cal_material();
                h.show_material(text);
                int money_temp=m.cal_money();
                h.money+=money_temp;
                text.append("您获得了"+money_temp+"个金币,您当前有"+h.money+"个金币"
                        +System.lineSeparator());
                int exp_temp=m.cal_exp(h);
                text.append("您获得了"+exp_temp+"点经验值"
                        +System.lineSeparator());
                h.exp_temp+=exp_temp;
                text.append(h.uplevel()
                        +System.lineSeparator());
                text.append(System.lineSeparator());
            }else {
                text.append("您消灭了boss" + System.lineSeparator());
                text.append(System.lineSeparator());
                material material_temp = m.generate();
                material material_temp2 = m.generate();
                text.append("您获得了" + material_temp.name + System.lineSeparator());
                text.append("您获得了" + material_temp2.name + System.lineSeparator());
                h.ma.add(material_temp);
                h.ma.add(material_temp2);
                h.cal_material();
                h.show_material(text);
                int money_temp = m.cal_money();
                h.money += money_temp;
                text.append("您获得了" + money_temp + "个金币,您当前有" + h.money + "个金币" + System.lineSeparator());
                int exp_temp = m.cal_exp(h);
                text.append("您获得了" + exp_temp + "点经验值" + System.lineSeparator());
                h.exp_temp += exp_temp;
                text.append(h.uplevel() + System.lineSeparator());
                text.append(System.lineSeparator());
            }
        }
    }
    public void m_action(JTextArea text,JFrame jf) {
        int a = (int)(Math.random()*4);
        if(h.eq[4]!=null) {
            boolean avoid_temp=h.eq[4].avoid(h);
            if(avoid_temp==false) {
                if (a == 0 && f_dizzy==false) {
                    m.attack(h);
                    text.append("怪兽发动了进攻" + System.lineSeparator());
                    text.append(System.lineSeparator());
                    if(h.eq[4]!=null) {
                        int recover_temp=h.eq[4].recover(h);
                        text.append("永夜守护为您回复了"+recover_temp+"滴血"+ System.lineSeparator());
                        h.life+=recover_temp;
                    }
                    h.show(text);
                } else if (a == 1 && f_dizzy==false) {
                    m.magic(h);
                    text.append("怪兽使用了魔法攻击"+System.lineSeparator());
                    text.append(System.lineSeparator());
                    if(h.eq[4]!=null) {
                        int recover_temp=h.eq[4].recover(h);
                        text.append("永夜守护为您回复了"+recover_temp+"滴血"+ System.lineSeparator());
                        h.life+=recover_temp;
                    }
                    h.show(text);
                } else if (a == 2 && f_dizzy==false) {
                    m.defense();
                    text.append("怪兽进行了防御"+ System.lineSeparator());
                    text.append(System.lineSeparator());
                    m.show(text);
                } else if (a == 3 && f_dizzy==false) {
                    m.life();
                    text.append("怪兽回复了生命值"+ System.lineSeparator());
                    text.append(System.lineSeparator());
                    m.show(text);
                }
                if(h.life<=0){
                    if(h.eq[2]!=null) {
                        boolean f_knife = h.eq[2].knife(h);
                        if(f_knife==false) {//优先判定名刀
                            if(h.eq[3]!=null) {
                                boolean f_relife = h.eq[3].relife(h);
                                if(f_relife==false) {
                                    text.append("很抱歉，您输了，失去所有材料和装备和金币，保留等级"+ System.lineSeparator());
                                    h.death();
                                    tips("很抱歉，您输了，失去所有材料和装备和金币，保留等级",jf);
                                }else {
                                    text.append("复活甲保护了你，并回满了你的状态，但是这是最后一次了"+ System.lineSeparator());
                                    h.life=h.maxlife;
                                }
                            }
                        }else {
                            text.append("名刀保护了你，保留了一滴血给您，但是这是最后一次了"+ System.lineSeparator());
                            h.life=1;
                        }
                    }else {
                        if(h.eq[3]!=null) {
                            boolean f_relife = h.eq[3].relife(h);
                            if(f_relife==false) {
                                text.append("很抱歉，您输了，失去所有材料和装备和金币，保留等级"+ System.lineSeparator());
                                h.death();
                                tips("很抱歉，您输了，失去所有材料和装备和金币，保留等级",jf);
                            }else {
                                text.append("复活甲保护了你，并回满了你的状态，但是这是最后一次了"+ System.lineSeparator());
                                h.life=h.maxlife;
                            }
                        }else {
                            text.append("很抱歉，您输了，失去所有材料和装备和金币，保留等级"+ System.lineSeparator());
                            h.death();
                            tips("很抱歉，您输了，失去所有材料和装备和金币，保留等级",jf);
                        }
                    }
                }
            }else {
                text.append("Lucky!您闪开了"+ System.lineSeparator());
            }
        }else {
            if (a == 0 && f_dizzy==false) {
                m.attack(h);
                text.append("怪兽发动了进攻"+ System.lineSeparator());
                text.append(System.lineSeparator());
                if(h.eq[4]!=null) {
                    int recover_temp=h.eq[4].recover(h);
                    text.append("永夜守护为您回复了"+recover_temp+"滴血"+ System.lineSeparator());
                    h.life+=recover_temp;
                }
                h.show(text);
            } else if (a == 1 && f_dizzy==false) {
                m.magic(h);
                text.append("怪兽使用了魔法攻击"+ System.lineSeparator());
                text.append(System.lineSeparator());
                if(h.eq[4]!=null) {
                    int recover_temp=h.eq[4].recover(h);
                    text.append("永夜守护为您回复了"+recover_temp+"滴血"+ System.lineSeparator());
                    h.life+=recover_temp;
                }
                h.show(text);
            } else if (a == 2 && f_dizzy==false) {
                m.defense();
                text.append("怪兽进行了防御"+ System.lineSeparator());
                text.append(System.lineSeparator());
                m.show(text);
            } else if (a == 3 && f_dizzy==false) {
                m.life();
                text.append("怪兽回复了生命值"+ System.lineSeparator());
                text.append(System.lineSeparator());
                m.show(text);
            }
            if(h.life<=0){
                if(h.eq[2]!=null) {
                    boolean f_knife = h.eq[2].knife(h);
                    if(f_knife==false) {//优先判定名刀
                        if(h.eq[3]!=null) {
                            boolean f_relife = h.eq[3].relife(h);
                            if(f_relife==false) {
                                text.append("很抱歉，您输了，失去所有材料和装备和金币，保留等级"+ System.lineSeparator());
                                h.death();
                                tips("很抱歉，您输了，失去所有材料和装备和金币，保留等级",jf);
                            }else {
                                text.append("复活甲保护了你，并回满了您的状态，但是这是最后一次了"+ System.lineSeparator());
                                h.life=h.maxlife;
                            }
                        }
                    }else {
                        text.append("名刀保护了你，保留了一滴血给您，但是这是最后一次了"+ System.lineSeparator());
                        h.life=1;
                    }
                }else {
                    if(h.eq[3]!=null) {
                        boolean f_relife = h.eq[3].relife(h);
                        if(f_relife==false) {
                            text.append("很抱歉，您输了，失去所有材料和装备和金币，保留等级"+ System.lineSeparator());
                            h.death();
                            tips("很抱歉，您输了，失去所有材料和装备和金币，保留等级",jf);
                        }else {
                            text.append("复活甲保护了你，并回满了你的状态，但是这是最后一次了"+ System.lineSeparator());
                            h.life=h.maxlife;
                        }
                    }else {
                        text.append("很抱歉，您输了，失去所有材料和装备和金币，保留等级"+ System.lineSeparator());
                        h.death();
                        tips("很抱歉，您输了，失去所有材料和装备和金币，保留等级",jf);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        chuanqi_pro c = new chuanqi_pro();
        c.xuanjue();
    }
}
