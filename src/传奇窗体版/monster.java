package 传奇窗体版;

import java.io.Serializable;

import javax.swing.JTextArea;

public abstract class monster implements offensive,defend,health,spell,Serializable{
    int attack;
    int defense;
    int life;
    int maxlife;
    int magic;
    int power;
    int exp;
    int boss;
    int money;
    String name;
    abstract double cal_power() ;
    public int cal_exp(hero h) {
        int exp = (int) (Math.random()*h.exp*0.3+7);
        return exp;
    }
    public int cal_money() {
        int money = (int) (Math.random()*900+100);
        return money;
    }
    public void Random(hero h) {
        int i =(int)(Math.random()*4);
        if (i==0) {
            attack(h);
            System.out.println("怪兽向您发动进攻");
        }
        if(i==1) {
            magic(h);
            System.out.println("怪兽向您发动魔法进攻");
        }
        if(i==2) {
            life();
            System.out.println("怪兽恢复");
        }
        if(i==3) {
            defense();
            System.out.println("怪兽防御提高");
        }
    }
    public equip drop(JTextArea text){
        int r = (int)(Math.random()*7);
        if(r == 0) {
            text.append("您获得了一个帽子！"+System.lineSeparator());
            System.out.println("您获得了一个帽子！");
            return new hat();
        }
        else if(r == 1) {
            text.append("您获得了一件武器！"+System.lineSeparator());
            System.out.println("您获得了一件武器！");
            return new weapons();
        }
        else if(r == 2) {
            text.append("您获得了一件衣服！"+System.lineSeparator());
            System.out.println("您获得了一件衣服！");
            return new cloths();
        }
        else if(r == 3) {
            text.append("您获得了一条裤子！"+System.lineSeparator());
            System.out.println("您获得了一条裤子！");
            return new trousers();
        }
        else if(r == 4) {
            text.append("您获得了一双鞋子！"+System.lineSeparator());
            System.out.println("您获得了一双鞋子！");
            return new shoe();
        }
        else if(r == 5) {
            text.append("您获得了一条项链！"+System.lineSeparator());
            System.out.println("您获得了一条项链！");
            return new necklace();
        } else {
            text.append("您获得了一个戒指！"+System.lineSeparator());
            System.out.println("您获得了一个戒指！");
            return new ring();
        }
    }
    public material generate() {
        int r = (int)(Math.random()*5);
        if(r == 0) {
            return new wood();
        }
        else if(r == 1) {
            return new stone();
        }
        else if(r == 2) {
            return new water();
        }
        else if(r == 3) {
            return new stones_pro();
        }
        else {
            return new stones_recreat();
        }
    }
    public void attack(monster m) {}//不用
    public void attack(hero h) {
        int extra=Math.abs(attack-h.defense);
        if(attack>h.defense) {
            h.life=(int)((h.life-(h.defense*(attack-h.defense)/100)-this.magic*0.9-extra)*(1-h.skill[8]));
        }else {
            h.life=(int)((h.life-this.attack*0.1-this.magic*0.9)*(1-h.skill[8]));
        }
    }
    public void magic(monster m) {}//不用
    public void magic(hero h) {
        h.life=(int)((h.life-this.magic*1.3)*(1-h.skill[8]));
    }
    public void life() {
        life =life +(int)((maxlife-life)*0.3);
    }
    public void defense() {
        defense*=1.2;
    }
    public void show(JTextArea text){
        text.append("怪兽" + System.lineSeparator());
        text.append("攻击力：" + attack + System.lineSeparator());
        text.append("防御力：" + defense + System.lineSeparator());
        text.append("当前生命值：" + life + System.lineSeparator());
        text.append("最大生命值：" + maxlife + System.lineSeparator());
        text.append("法力值：" + magic + System.lineSeparator());
        text.append("战力值：" + power + System.lineSeparator());
        text.append(System.lineSeparator()); // 添加一个空行
    }
}
