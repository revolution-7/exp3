package 传奇窗体版;

import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public abstract class equip implements Serializable{
    int attack,defense,maxlife,magic;
    double quality;
    int quality_level;
    protected int id;
    double [] skill =new double[10];
    abstract void show(JTextArea text) ;
    abstract void show(JLabel l1) ;
    public void up(int id,hero h) {
        switch(id)
        {
            case  0 :
                defense+=5;
                maxlife+=5;
                h.defense+=5;
                h.maxlife+=5;
                if(skill[0]<=0.5) {
                    skill[0]+=0.05;
                    h.skill[0]+=0.05;
                }
                else if(skill[0]>0.5) {
                    double dif=Math.abs(skill[0]-1);
                    skill[0]+=dif*0.13;
                    h.skill[0]+=dif*0.13;
                }
                break;
            case  1 :
                attack+=5;
                h.attack+=5;
                if(skill[1]<=0.5) {
                    skill[1]+=0.05;
                    h.skill[1]+=0.05;
                }else if(skill[1]>0.5) {
                    double dif=Math.abs(skill[1]-1);
                    skill[1]+=dif*0.13;
                    h.skill[1]+=dif*0.13;
                }
                if(skill[2]<=1) {
                    skill[2]+=0.05;
                    h.skill[2]+=0.05;
                }else if(skill[2]>1) {
                    double dif=Math.abs(skill[2]-2);
                    skill[2]+=dif*0.13;
                    h.skill[2]+=dif*0.13;
                }
                break;
            case  2 :
                defense+=5;
                maxlife+=5;
                h.defense+=5;
                h.maxlife+=5;
                if(skill[5]<=0.3) {
                    skill[5]+=0.05;
                    h.skill[5]+=0.05;
                }else if(skill[5]>0.3) {
                    double dif=Math.abs(skill[5]-1);
                    skill[5]+=dif*0.07;
                    h.skill[5]+=dif*0.07;
                }if(skill[8]<=0.3) {
                skill[8]+=0.05;
                h.skill[8]+=0.05;
            }else if(skill[8]>0.3) {
                double dif=Math.abs(skill[8]-1);
                skill[8]+=dif*0.07;
                h.skill[8]+=dif*0.07;
            }
                break;
            case  3 :
                defense+=5;
                maxlife+=5;
                h.defense+=5;
                h.maxlife+=5;
                if(skill[6]<=0.3) {
                    skill[6]+=0.05;
                    h.skill[6]+=0.05;
                }else if(skill[6]>0.3) {
                    double dif=Math.abs(skill[6]-1);
                    skill[6]+=dif*0.07;
                    h.skill[6]+=dif*0.07;
                }
                break;
            case  4 :
                defense+=5;
                maxlife+=5;
                h.defense+=5;
                h.maxlife+=5;
                if(skill[7]<=0.25) {
                    skill[7]+=0.05;
                    h.skill[7]+=0.05;
                }else if(skill[7]>0.25) {
                    double dif=Math.abs(skill[7]-1);
                    skill[7]+=dif*0.065;
                    h.skill[7]+=dif*0.065;
                }if(skill[9]<=0.3) {
                skill[9]+=0.05;
                h.skill[9]+=0.05;
            }else if(skill[9]>0.3) {
                double dif=Math.abs(skill[9]-1);
                skill[9]+=dif*0.07;
                h.skill[9]+=dif*0.07;
            }
                break;
            case  5 :
                magic+=5;
                h.magic+=5;
                if(skill[3]<=0.5) {
                    skill[3]+=0.05;
                    h.skill[3]+=0.05;
                }else if(skill[3]>0.5) {
                    double dif=Math.abs(skill[3]-1);
                    skill[3]+=dif*0.13;
                    h.skill[3]+=dif*0.13;
                }
                break;
            case  6 :
                magic+=5;
                h.magic+=5;
                if(skill[4]<=0.3) {
                    skill[4]+=0.05;
                    h.skill[4]+=0.05;
                }else if(skill[4]>0.3) {
                    double dif=Math.abs(skill[3]-1);
                    skill[4]+=dif*0.07;
                    h.skill[4]+=dif*0.07;
                }
                break;
        }
    }
    public equip recast(hero h,chuanqi_pro c,JFrame frame) {
        if(h.ma_num[4]>0&&h.money>=2000) {
            h.ma_num[4]--;
            h.money-=2000;
            switch(id)
            {
                case  0 :
                    return new hat();
                case  1 :
                    return new weapons();
                case  2 :
                    return new cloths();
                case  3 :
                    return new trousers();
                case  4 :
                    return new shoe();
                case  5 :
                    return new necklace();
                case  6 :
                    return new ring();
            }
        }else if(h.ma_num[4]<1){
            System.out.println("洗炼石不足");
            c.tips("洗炼石不足", frame);
        }else if(h.money<2000) {
            System.out.println("金币不足");
            c.tips("金币不足", frame);
        }
        return this;
    }
    public boolean dizzy(hero h) {
        double temp=Math.random();
        if(temp>h.skill[0]) {
            return false;//可以行动
        }else {
            return true;//不能行动
        }
    }
    public boolean knife(hero h) {
        double temp=Math.random();
        if(temp>h.skill[5]) {
            return false;//死
        }else {
            skill[5]=0;
            h.skill[5]=0;
            return true;//触发名刀
        }
    }
    public boolean highattack(hero h) {
        double temp=Math.random();
        if(temp>h.skill[1]) {
            return false;//普通攻击
        }else {
            return true;//暴击
        }
    }
    public void highattack_effect() {}
    public void break_defense(hero h) {}
    public void attack_buff_do(hero h) {};
    public boolean relife(hero h) {
        double temp=Math.random();
        if(temp>h.skill[6]) {
            return false;//死
        }else {
            skill[6]=0;
            h.skill[6]=0;
            return true;//复活
        }
    }
    public int recover(hero h) {
        if(h.life/h.maxlife<=0.3) {
            return (int)(h.maxlife*skill[7]);
        }else {
            return 0;
        }
    }
    public void attack_buff_ed(hero h) {}
    public boolean avoid(hero h) {
        double temp=Math.random();
        if(temp>h.skill[9]) {
            return false;//被击中了
        }else {
            return true;//闪开了
        }
    }
    public void creat() {//生命和防御
        quality=Math.random()*10;
        int num=(int) (Math.random()*10);
        int num2=(int) (Math.random()*10);
        if (quality <= 5) {
            defense = (int)(num * 0.5 + 1);
            maxlife = (int)(num2 * 0.5 + 1);
            quality_level=0;
        }
        else if (quality <= 7.5) {
            defense = (int)(num + 5);
            maxlife = (int)(num2 + 5);
            quality_level=1;
        }
        else if (quality <= 9) {
            defense = (int)(num * 1.5 + 15);
            maxlife = (int)(num2  * 1.5 + 15);
            quality_level=2;
        }
        else if (quality <= 9.5) {
            defense = (int)(num *4.5+ 30);
            maxlife = (int)(num2 *4.5 + 30);
            quality_level=3;
        }
        else if (quality <= 9.85) {
            defense = (int)(num * 7.5 + 75);
            maxlife = (int)(num2 * 7.5 + 75);
            quality_level=4;
        }
        else{
            defense = (int)(num * 15+ 150);
            maxlife = (int)(num2 * 15 + 150);
            quality_level=5;
        }
        //show();
    }
}