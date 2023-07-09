package 传奇窗体版;

import java.io.Serializable;

import javax.swing.*;

abstract class material implements Serializable{//有灵木、强化石、圣水、万能石、洗炼石
    String name;
    int id;
}
interface offensive{//攻击
    public void attack(monster m);
    public void attack(hero h);
}
interface defend{//防御
    public void defense();
}
interface health{//生命
    public void life();
}
interface spell{//法力
    public void magic(monster m);
    public void magic(hero h);
}
class warrior extends hero implements offensive,defend,health,spell,Serializable{//战士
    warrior(){
        job = "战士";
        attack = 20;
        defense = 15;
        maxlife=life = 20;
        magic = 2;
        power=(int)cal_power();
    }
    public double cal_power() {
        return attack*1.5+defense*1.5+maxlife*1.5+magic*0.5;
    }
    public void attack(monster m) {//战士，普通攻击伤害中等
        m.defense*=(1-skill[3]);
        double extra=Math.abs(attack-m.defense);
        if(attack>m.defense) {
            m.life=(int)((m.life-((m.defense*(attack-m.defense)/100)*1.25)-this.magic*0.3-extra)*(1+skill[4]));
        }else {
            m.life=(int)((m.life-this.attack*0.25-this.magic*0.3)*(1+skill[4]));
        }
        m.defense/=(1-skill[3]);
    }
    public void magic(monster m) {//战士法伤中等
        m.life = (int)((m.life -magic*1.3)*(1+skill[4]));
    }
    public void defense() {
        defense*=1.8;
    }
}
class mage extends hero implements offensive,defend,health,spell,Serializable{//法师
    mage(){
        job = "法师";
        attack = 3;
        defense = 3;
        maxlife=life = 7;
        magic = 18;
        power=(int)cal_power();
    }
    public double cal_power() {
        return attack*0.5+defense*0.5+maxlife*1+magic*2;
    }
    public void attack(monster m) {//法师伤害最低
        m.defense*=(1-skill[3]);
        double extra=Math.abs(attack-m.defense);
        if(attack>m.defense) {
            m.life=(int)((m.life-((m.defense*(attack-m.defense)/100)*0.75)-this.magic*0.9-extra)*(1+skill[4]));
        }else {
            m.life=(int)((m.life-this.attack*0.05-this.magic*0.9)*(1+skill[4]));
        }
        m.defense/=(1-skill[3]);
    }
    public void magic(monster m) {//法师法伤最高
        m.life = (int) ((m.life -magic*1.8)*(1+skill[4]));
    }
}
class archer extends hero implements offensive,defend,health,spell,Serializable{//弓箭手
    archer(){
        job = "弓箭手";
        attack = 17;
        defense = 6;
        maxlife=life = 9;
        magic = 3;
        power=(int)cal_power();
    }
    public double cal_power() {
        return attack*2+defense*1+maxlife*1+magic*0.5;
    }
    public void attack(monster m) {//射手伤害最高
        m.defense*=(1-skill[3]);
        double extra=Math.abs(attack-m.defense);
        if(attack>m.defense) {
            m.life=(int)((m.life-((m.defense*(attack-m.defense)/100)*1.5)-this.magic*0.5-extra)*(1+skill[4]));
        }else {
            m.life=(int)((m.life-this.attack*0.3-this.magic*0.5)*(1+skill[4]));
        }
        m.defense/=(1-skill[3]);
    }
    public void magic(monster m) {//射手法伤最低
        m.life = (int)((m.life -magic*1.1)*(1+skill[4]));
    }
}
class goblins extends monster implements offensive,defend,health,spell,Serializable{//哥布林
    goblins(hero h){
        name="哥布林";
        boss=0;
        attack = (int)(6+Math.random()*0.9*h.attack);
        defense = (int)(3+Math.random()*0.9*h.defense);
        maxlife=life = (int)(6+Math.random()*0.9*h.maxlife);
        magic = (int)(1+Math.random()*0.9*h.magic);
        power=(int)cal_power();
    }
    public double cal_power() {
        return attack*1.3+defense*1+maxlife*1+magic*0.7;
    }
}
class witch extends monster implements offensive,defend,health,spell,Serializable{//巫女
    witch(hero h){
        name="巫女";
        boss=0;
        attack = (int)(3+Math.random()*0.9*h.attack);
        defense = (int)(3+Math.random()*0.9*h.defense);
        maxlife=life = (int)(5+Math.random()*0.9*h.maxlife);
        magic = (int)(9+Math.random()*0.9*h.magic);
        power=(int)cal_power();
    }
    public double cal_power() {
        return attack*1+defense*1+maxlife*1+magic*1.3;
    }
}
class clown extends monster implements offensive,defend,health,spell,Serializable{//小丑
    clown(hero h){
        name="小丑";
        boss=0;
        attack = (int)(9+Math.random()*0.9*h.attack);
        defense = (int)(5+Math.random()*0.9*h.defense);
        maxlife=life = (int)(7+Math.random()*0.9*h.maxlife);
        magic = (int)(2+Math.random()*0.9*h.magic);
        power=(int)cal_power();
    }
    public double cal_power() {
        return attack*1.3+defense*1.3+maxlife*1+magic*0.7;
    }
}
class rampage extends monster implements offensive,defend,health,spell,Serializable{//狂暴
    rampage(hero h){
        name="狂暴";
        boss=0;
        attack = (int)(9+Math.random()*h.attack);
        defense = (int)(9+Math.random()*h.defense);
        maxlife=life = (int)(9+Math.random()*h.maxlife);
        magic = (int)(9+Math.random()*h.magic);
        power=(int)cal_power();
    }
    public double cal_power() {
        return attack*1.1+defense*1.1+maxlife*1.1+magic*1.1;
    }
}
class bossgoblins extends monster implements offensive,defend,health,spell,Serializable{//哥布林boss
    bossgoblins(hero h){
        name="哥布林boss";
        boss=1;
        attack = (int)(60+Math.random()*0.95*h.attack);
        defense = (int)(30+Math.random()*0.95*h.defense);
        maxlife=life = (int)(60+Math.random()*0.95*h.maxlife);
        magic = (int)(10+Math.random()*0.95*h.magic);
        power=(int)cal_power();
    }
    public double cal_power() {
        return attack*1.3+defense*1+maxlife*1+magic*0.7;
    }
    public int cal_exp(hero h) {
        int exp = (int) (Math.random()*h.exp+1000);
        return exp;
    }
    public int cal_money() {
        int money = (int) (Math.random()*9000+1000);
        return money;
    }
}
class bosswitch extends monster implements offensive,defend,health,spell,Serializable{//巫女boss
    bosswitch(hero h){
        name="巫女boss";
        boss=1;
        attack = (int)(30+Math.random()*0.95*h.attack);
        defense = (int)(30+Math.random()*0.95*h.defense);
        maxlife=life = (int)(50+Math.random()*0.95*h.maxlife);
        magic = (int)(90+Math.random()*0.95*h.magic);
        power=(int)cal_power();
    }
    public double cal_power() {
        return attack*1+defense*1+maxlife*1+magic*1.3;
    }
    public int cal_exp(hero h) {
        int exp = (int) (Math.random()*h.exp+1000);
        return exp;
    }
    public int cal_money() {
        int money = (int) (Math.random()*9000+1000);
        return money;
    }
}
class bossclown extends monster implements offensive,defend,health,spell,Serializable{//小丑boss
    bossclown(hero h){
        name="小丑boss";
        boss=1;
        attack = (int)(90+Math.random()*0.95*h.attack);
        defense = (int)(50+Math.random()*0.95*h.defense);
        maxlife=life = (int)(70+Math.random()*0.95*h.maxlife);
        magic = (int)(20+Math.random()*0.95*h.magic);
        power=(int)cal_power();
    }
    public double cal_power() {
        return attack*1.3+defense*1.3+maxlife*1+magic*0.7;
    }
    public int cal_exp(hero h) {
        int exp = (int) (Math.random()*h.exp+1000);
        return exp;
    }
    public int cal_money() {
        int money = (int) (Math.random()*9000+1000);
        return money;
    }
}
class bossrampage extends monster implements offensive,defend,health,spell,Serializable{//狂暴boss
    bossrampage(hero h){
        name="狂暴boss";
        boss=1;
        attack = (int)(90+Math.random()*h.attack);
        defense = (int)(90+Math.random()*h.defense);
        maxlife=life = (int)(90+Math.random()*h.maxlife);
        magic = (int)(90+Math.random()*h.magic);
        power=(int)cal_power();
    }
    public double cal_power() {
        return attack*1.1+defense*1.1+maxlife*1.1+magic*1.1;
    }
    public int cal_exp(hero h) {
        int exp = (int) (Math.random()*h.exp+1000);
        return exp;
    }
    public int cal_money() {
        int money = (int) (Math.random()*9000+1000);
        return money;
    }
}
class hat extends equip implements dizzy,Serializable{//帽子
    hat(){
        id=0;
        super.creat();
        if (quality_level==0) {
            skill[0]=(Math.random()*5)/100;
        }
        else if (quality_level==1) {
            skill[0]=(Math.random()*5+5)/100;
        }
        else if (quality_level==2) {
            skill[0]=(Math.random()*5+10)/100;
        }
        else if (quality_level==3) {
            skill[0]=(Math.random()*5+15)/100;
        }
        else if (quality_level==4) {
            skill[0]=(Math.random()*10+20)/100;
        }
        else{
            skill[0]=(Math.random()*10+30)/100;
        }
    }
    public void show(JTextArea text) {
        switch (quality_level) {
            case 0:
                text.append("白色品质" + System.lineSeparator());
                break;
            case 1:
                text.append("绿色品质" + System.lineSeparator());
                break;
            case 2:
                text.append("蓝色品质" + System.lineSeparator());
                break;
            case 3:
                text.append("紫色品质" + System.lineSeparator());
                break;
            case 4:
                text.append("金色品质" + System.lineSeparator());
                break;
            case 5:
                text.append("传奇品质" + System.lineSeparator());
                break;
        }
        text.append("防御加成：" + defense + System.lineSeparator());
        text.append("最大生命加成：" + maxlife + System.lineSeparator());
        text.append("晕眩率：" + (int) (skill[0] * 100) + "%" + System.lineSeparator());
        text.append(System.lineSeparator());
    }
    public void show(JLabel label) {
        switch (quality_level) {
            case 0:
                label.setText(label.getText() + "白色品质"+"\n");
                break;
            case 1:
                label.setText(label.getText() + "绿色品质"+"\n");
                break;
            case 2:
                label.setText(label.getText() + "蓝色品质"+"\n");
                break;
            case 3:
                label.setText(label.getText() + "紫色品质"+"\n");
                break;
            case 4:
                label.setText(label.getText() + "金色品质"+"\n");
                break;
            case 5:
                label.setText(label.getText() + "传奇品质"+"\n");
                break;
        }
        label.setText(label.getText() + "防御加成：" + defense + "\n");
        label.setText(label.getText() + "最大生命加成：" + maxlife + "\n");
        label.setText(label.getText() + "晕眩率：" + (int)(skill[0] * 100) + "%" + "\n");
    }
}
class weapons extends equip implements highattack,highattack_effect,Serializable{//武器
    weapons(){
        id=1;
        quality=Math.random()*10;
        int num=(int) (Math.random()*10);
        if (quality <= 5) {
            attack = (int)(num * 0.5+ 1);
            quality_level=0;
        }
        else if (quality <= 7.5) {
            attack = (int)(num  + 5);
            quality_level=1;
        }
        else if (quality <= 9) {
            attack = (int)(num * 1.5 + 15);
            quality_level=2;
        }
        else if (quality <= 9.5) {
            attack = (int)(num * 4.5 + 30);
            quality_level=3;
        }
        else if (quality <= 9.85) {
            attack = (int)(num * 7.5 + 75);
            quality_level=4;
        }
        else{
            attack = (int)(num *15+ 150);
            quality_level=5;
        }

        if (quality_level==0) {
            skill[1]=(Math.random()*5)/100;
            skill[2]=(Math.random()*10)/100;
        }
        else if (quality_level==1) {
            skill[1]=(Math.random()*5+5)/100;
            skill[2]=(Math.random()*10+10)/100;
        }
        else if (quality_level==2) {
            skill[1]=(Math.random()*5+10)/100;
            skill[2]=(Math.random()*10+20)/100;
        }
        else if (quality_level==3) {
            skill[1]=(Math.random()*5+15)/100;
            skill[2]=(Math.random()*20+30)/100;
        }
        else if (quality_level==4) {
            skill[1]=(Math.random()*10+20)/100;
            skill[2]=(Math.random()*30+50)/100;
        }
        else{
            skill[1]=(Math.random()*10+30)/100;
            skill[2]=(Math.random()*40+80)/100;
        }
    }
    public void show(JTextArea text) {
        switch (quality_level) {
            case 0:
                text.append("白色品质" + System.lineSeparator());
                break;
            case 1:
                text.append("绿色品质" + System.lineSeparator());
                break;
            case 2:
                text.append("蓝色品质" + System.lineSeparator());
                break;
            case 3:
                text.append("紫色品质" + System.lineSeparator());
                break;
            case 4:
                text.append("金色品质" + System.lineSeparator());
                break;
            case 5:
                text.append("传奇品质" + System.lineSeparator());
                break;
        }
        text.append("攻击加成：" + attack + System.lineSeparator());
        text.append("暴击率：" + (int) (skill[1] * 100) + "%" + System.lineSeparator());
        text.append("暴击效果加成：" + (int) (skill[2] * 100) + "%" + System.lineSeparator());
        text.append(System.lineSeparator());
    }
    public void show(JLabel label) {
        switch (quality_level) {
            case 0:
                label.setText(label.getText() + "白色品质\n");
                break;
            case 1:
                label.setText(label.getText() + "绿色品质\n");
                break;
            case 2:
                label.setText(label.getText() + "蓝色品质\n");
                break;
            case 3:
                label.setText(label.getText() + "紫色品质\n");
                break;
            case 4:
                label.setText(label.getText() + "金色品质\n");
                break;
            case 5:
                label.setText(label.getText() + "传奇品质\n");
                break;
        }
        label.setText(label.getText() + "攻击加成：" + attack + "\n");
        label.setText(label.getText() + "暴击率：" + (int) (skill[1] * 100) + "%"  + "\n");
        label.setText(label.getText() + "暴击效果加成：" + (int)(skill[2] * 100) + "%" + "\n");
    }
}
class cloths extends equip implements knife,attack_buff_ed,Serializable{//衣服
    cloths(){
        id=2;
        super.creat();

        if (quality_level==0) {
            skill[5]=(Math.random()*3)/100;
            skill[8]=(Math.random()*3)/100;
        }
        else if (quality_level==1) {
            skill[5]=(Math.random()*4+3)/100;
            skill[8]=(Math.random()*4+3)/100;
        }
        else if (quality_level==2) {
            skill[5]=(Math.random()*5+7)/100;
            skill[8]=(Math.random()*5+7)/100;
        }
        else if (quality_level==3) {
            skill[5]=(Math.random()*6+12)/100;
            skill[8]=(Math.random()*6+12)/100;
        }
        else if (quality_level==4) {
            skill[5]=(Math.random()*6+18)/100;
            skill[8]=(Math.random()*6+18)/100;
        }
        else{
            skill[5]=(Math.random()*6+24)/100;
            skill[8]=(Math.random()*6+24)/100;
        }
    }
    public void show(JTextArea text) {
        switch (quality_level) {
            case 0:
                text.append("白色品质" + System.lineSeparator());
                break;
            case 1:
                text.append("绿色品质" + System.lineSeparator());
                break;
            case 2:
                text.append("蓝色品质" + System.lineSeparator());
                break;
            case 3:
                text.append("紫色品质" + System.lineSeparator());
                break;
            case 4:
                text.append("金色品质" + System.lineSeparator());
                break;
            case 5:
                text.append("传奇品质" + System.lineSeparator());
                break;
        }
        text.append("防御加成：" + defense + System.lineSeparator());
        text.append("最大生命加成：" + maxlife + System.lineSeparator());
        text.append("名刀触发概率：" + (int) (skill[5] * 100) + "%" + System.lineSeparator());
        text.append("免伤概率：" + (int) (skill[8] * 100) + "%" + System.lineSeparator());
        text.append(System.lineSeparator());
    }
    public void show(JLabel label) {
        switch (quality_level) {
            case 0:
                label.setText(label.getText() + "白色品质\n");
                break;
            case 1:
                label.setText(label.getText() + "绿色品质\n");
                break;
            case 2:
                label.setText(label.getText() + "蓝色品质\n");
                break;
            case 3:
                label.setText(label.getText() + "紫色品质\n");
                break;
            case 4:
                label.setText(label.getText() + "金色品质\n");
                break;
            case 5:
                label.setText(label.getText() + "传奇品质\n");
                break;
        }
        label.setText(label.getText() + "防御加成：" + defense + "\n");
        label.setText(label.getText() + "最大生命加成：" + maxlife + "\n");
        label.setText(label.getText() + "名刀触发概率：" + (int)(skill[5] * 100) + "%" + "\n");
        label.setText(label.getText() + "免伤概率：" + (int)(skill[8] * 100) + "%" + "\n");
    }
}
class trousers extends equip implements relife,Serializable{//裤子
    trousers(){
        id=3;
        super.creat();
        if (quality_level==0) {
            skill[6]=(Math.random()*3)/100;
        }
        else if (quality_level==1) {
            skill[6]=(Math.random()*4+3)/100;
        }
        else if (quality_level==2) {
            skill[6]=(Math.random()*5+7)/100;
        }
        else if (quality_level==3) {
            skill[6]=(Math.random()*6+12)/100;
        }
        else if (quality_level==4) {
            skill[6]=(Math.random()*6+18)/100;
        }
        else{
            skill[6]=(Math.random()*6+24)/100;
        }
    }
    public void show(JTextArea text) {
        switch (quality_level) {
            case 0:
                text.append("白色品质" + System.lineSeparator());
                break;
            case 1:
                text.append("绿色品质" + System.lineSeparator());
                break;
            case 2:
                text.append("蓝色品质" + System.lineSeparator());
                break;
            case 3:
                text.append("紫色品质" + System.lineSeparator());
                break;
            case 4:
                text.append("金色品质" + System.lineSeparator());
                break;
            case 5:
                text.append("传奇品质" + System.lineSeparator());
                break;
        }
        text.append("防御加成：" + defense + System.lineSeparator());
        text.append("最大生命加成：" + maxlife + System.lineSeparator());
        text.append("复活概率：" + (int) (skill[6] * 100) + "%" + System.lineSeparator());
        text.append(System.lineSeparator());
    }
    public void show(JLabel label) {
        switch (quality_level) {
            case 0:
                label.setText(label.getText() + "白色品质\n");
                break;
            case 1:
                label.setText(label.getText() + "绿色品质\n");
                break;
            case 2:
                label.setText(label.getText() + "蓝色品质\n");
                break;
            case 3:
                label.setText(label.getText() + "紫色品质\n");
                break;
            case 4:
                label.setText(label.getText() + "金色品质\n");
                break;
            case 5:
                label.setText(label.getText() + "传奇品质\n");
                break;
        }
        label.setText(label.getText() + "防御加成：" + defense + "\n");
        label.setText(label.getText() + "最大生命加成：" + maxlife + "\n");
        label.setText(label.getText() + "复活概率：" + (int)(skill[6] * 100) + "%" + "\n");
    }
}
class shoe extends equip implements recover,avoid,Serializable{//鞋子
    shoe(){
        id=4;
        super.creat();

        if (quality_level==0) {
            skill[7]=(Math.random()*2)/100;
            skill[9]=(Math.random()*3)/100;
        }
        else if (quality_level==1) {
            skill[7]=(Math.random()*3+2)/100;
            skill[9]=(Math.random()*4+3)/100;
        }
        else if (quality_level==2) {
            skill[7]=(Math.random()*3+5)/100;
            skill[9]=(Math.random()*5+7)/100;
        }
        else if (quality_level==3) {
            skill[7]=(Math.random()*4+8)/100;
            skill[9]=(Math.random()*6+12)/100;
        }
        else if (quality_level==4) {
            skill[7]=(Math.random()*6+12)/100;
            skill[9]=(Math.random()*6+18)/100;
        }
        else{
            skill[7]=(Math.random()*7+18)/100;
            skill[9]=(Math.random()*6+24)/100;
        }
    }
    public void show(JTextArea text) {
        switch (quality_level) {
            case 0:
                text.append("白色品质" + System.lineSeparator());
                break;
            case 1:
                text.append("绿色品质" + System.lineSeparator());
                break;
            case 2:
                text.append("蓝色品质" + System.lineSeparator());
                break;
            case 3:
                text.append("紫色品质" + System.lineSeparator());
                break;
            case 4:
                text.append("金色品质" + System.lineSeparator());
                break;
            case 5:
                text.append("传奇品质" + System.lineSeparator());
                break;
        }
        text.append("防御加成：" + defense + System.lineSeparator());
        text.append("最大生命加成：" + maxlife + System.lineSeparator());
        text.append("永夜回血比例：" + (int) (skill[7] * 100) + "%" + System.lineSeparator());
        text.append("闪避率：" + (int) (skill[9] * 100) + "%" + System.lineSeparator());
        text.append(System.lineSeparator());
    }
    public void show(JLabel label) {
        switch (quality_level) {
            case 0:
                label.setText(label.getText() + "白色品质\n");
                break;
            case 1:
                label.setText(label.getText() + "绿色品质\n");
                break;
            case 2:
                label.setText(label.getText() + "蓝色品质\n");
                break;
            case 3:
                label.setText(label.getText() + "紫色品质\n");
                break;
            case 4:
                label.setText(label.getText() + "金色品质\n");
                break;
            case 5:
                label.setText(label.getText() + "传奇品质\n");
                break;
        }
        label.setText(label.getText() + "防御加成：" + defense + "\n");
        label.setText(label.getText() + "最大生命加成：" + maxlife + "\n");
        label.setText(label.getText() + "永夜回血比例：" + (int)(skill[7] * 100) + "%" + "\n");
        label.setText(label.getText() + "闪避率：" + (int)(skill[9] * 100) + "%" + "\n");
    }
}
class necklace extends equip implements break_defense,Serializable{//项链
    necklace(){
        id=5;
        quality=Math.random()*10;
        int num=(int) (Math.random()*10);
        if (quality <= 5) {
            magic = (int)(num * 0.5 + 1);
            quality_level=0;
        }
        else if (quality <= 7.5) {
            magic = (int)(num  + 5);
            quality_level=1;
        }
        else if (quality <= 9) {
            magic = (int)(num *1.5+ 15);
            quality_level=2;
        }
        else if (quality <= 9.5) {
            magic = (int)(num *4.5 + 30);
            quality_level=3;
        }
        else if (quality <= 9.85) {
            magic = (int)(num * 7.5 + 75);
            quality_level=4;
        }
        else{
            magic = (int)(num * 15 + 150);
            quality_level=5;
        }

        if (quality_level==0) {
            skill[3]=(Math.random()*5)/100;
        }
        else if (quality_level==1) {
            skill[3]=(Math.random()*5+5)/100;
        }
        else if (quality_level==2) {
            skill[3]=(Math.random()*5+10)/100;
        }
        else if (quality_level==3) {
            skill[3]=(Math.random()*5+15)/100;
        }
        else if (quality_level==4) {
            skill[3]=(Math.random()*10+20)/100;
        }
        else{
            skill[3]=(Math.random()*10+30)/100;
        }
    }
    public void show(JTextArea text) {
        switch (quality_level) {
            case 0:
                text.append("白色品质" + System.lineSeparator());
                break;
            case 1:
                text.append("绿色品质" + System.lineSeparator());
                break;
            case 2:
                text.append("蓝色品质" + System.lineSeparator());
                break;
            case 3:
                text.append("紫色品质" + System.lineSeparator());
                break;
            case 4:
                text.append("金色品质" + System.lineSeparator());
                break;
            case 5:
                text.append("传奇品质" + System.lineSeparator());
                break;
        }
        text.append("法力加成：" + magic + System.lineSeparator());
        text.append("破防加成：" + (int) (skill[3] * 100) + "%" + System.lineSeparator());
        text.append(System.lineSeparator());
    }
    public void show(JLabel label) {
        switch (quality_level) {
            case 0:
                label.setText(label.getText() + "白色品质\n");
                break;
            case 1:
                label.setText(label.getText() + "绿色品质\n");
                break;
            case 2:
                label.setText(label.getText() + "蓝色品质\n");
                break;
            case 3:
                label.setText(label.getText() + "紫色品质\n");
                break;
            case 4:
                label.setText(label.getText() + "金色品质\n");
                break;
            case 5:
                label.setText(label.getText() + "传奇品质\n");
                break;
        }
        label.setText(label.getText() + "法力加成：" + magic + "\n");
        label.setText(label.getText() + "破防加成：" + (int)(skill[3] * 100) + "%" + "\n");
    }
}
class ring extends equip implements attack_buff_do,Serializable{//戒指
    ring(){
        id=6;
        quality=Math.random()*10;
        int num=(int) (Math.random()*10);
        if (quality <= 5) {
            magic = (int)(num * 0.5 + 1);
            quality_level=0;
        }
        else if (quality <= 7.5) {
            magic = (int)(num  + 5);
            quality_level=1;
        }
        else if (quality <= 9) {
            magic = (int)(num *1.5+ 15);
            quality_level=2;
        }
        else if (quality <= 9.5) {
            magic = (int)(num *4.5 + 30);
            quality_level=3;
        }
        else if (quality <= 9.85) {
            magic = (int)(num * 7.5 + 75);
            quality_level=4;
        }
        else{
            magic = (int)(num * 15 + 150);
            quality_level=5;
        }

        if (quality_level==0) {
            skill[4]=(Math.random()*5)/100;
        }
        else if (quality_level==1) {
            skill[4]=(Math.random()*5+5)/100;
        }
        else if (quality_level==2) {
            skill[4]=(Math.random()*5+10)/100;
        }
        else if (quality_level==3) {
            skill[4]=(Math.random()*5+15)/100;
        }
        else if (quality_level==4) {
            skill[4]=(Math.random()*10+20)/100;
        }
        else{
            skill[4]=(Math.random()*10+30)/100;
        }
    }
    public void show(JTextArea text) {
        switch (quality_level) {
            case 0:
                text.append("白色品质" + System.lineSeparator());
                break;
            case 1:
                text.append("绿色品质" + System.lineSeparator());
                break;
            case 2:
                text.append("蓝色品质" + System.lineSeparator());
                break;
            case 3:
                text.append("紫色品质" + System.lineSeparator());
                break;
            case 4:
                text.append("金色品质" + System.lineSeparator());
                break;
            case 5:
                text.append("传奇品质" + System.lineSeparator());
                break;
        }
        text.append("法力加成：" + magic + System.lineSeparator());
        text.append("伤害加成：" + (int) (skill[4] * 100) + "%" + System.lineSeparator());
        text.append(System.lineSeparator());
    }
    public void show(JLabel label) {
        switch (quality_level) {
            case 0:
                label.setText(label.getText() + "白色品质\n");
                break;
            case 1:
                label.setText(label.getText() + "绿色品质\n");
                break;
            case 2:
                label.setText(label.getText() + "蓝色品质\n");
                break;
            case 3:
                label.setText(label.getText() + "紫色品质\n");
                break;
            case 4:
                label.setText(label.getText() + "金色品质\n");
                break;
            case 5:
                label.setText(label.getText() + "传奇品质\n");
                break;
        }
        label.setText(label.getText() + "法力加成：" + magic + "\n");
        label.setText(label.getText() + "伤害加成：" + (int)(skill[4] * 100) + "%" + "\n");
    }
}
class wood extends material implements Serializable{//木
    wood(){
        id=0;
        name="木材";
    }
}
class stone extends material implements Serializable{//石
    stone(){
        id=1;
        name="强化石";
    }
}
class water extends material implements Serializable{//圣水
    water(){
        id=2;
        name="圣水";
    }
}
class stones_pro extends material implements Serializable{//万能石
    stones_pro(){
        id=3;
        name="万能石";
    }
}
class stones_recreat extends material implements Serializable{//洗炼石
    stones_recreat(){
        id=4;
        name="洗炼石";
    }
}
interface dizzy{//十大接口之一，晕眩，attack
    public boolean dizzy(hero h);
}
interface highattack{//暴击,attack
    public boolean highattack(hero h);
}
interface highattack_effect{//暴击效果,attack
    public void highattack_effect();
}
interface break_defense{//破防,attack
    public void break_defense(hero h);
}
interface attack_buff_do{//越战越勇，每次造成伤害，有概率伤害有一定增益，attack
    public void attack_buff_do(hero h);
}
interface knife{//名刀，概率触发,被attack
    public boolean knife(hero h);
}
interface relife{//复活甲，概率触发，被attack
    public boolean relife(hero h);
}
interface recover{//永夜守护，低于多少生命值，恢复一定生命值，被attack
    public int recover(hero h);
}
interface attack_buff_ed{//越战越勇，每次受到伤害，有概率防御有一定增益，被attack
    public void attack_buff_ed(hero h);
}
interface avoid{//十大接口之十，闪避,被attack
    public boolean avoid(hero h);
}