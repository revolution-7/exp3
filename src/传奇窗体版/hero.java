package 传奇窗体版;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import 传奇窗体版.chuanqi_pro;

public abstract class hero implements offensive,defend,health,spell,Serializable{
    String name,job;
    int attack,defense,life,maxlife,magic,level=1,money=0;
    protected int power;
    double exp=10,exp_temp=0;
    double [] skill =new double[10];
    transient Scanner sc=new Scanner(System.in);
    ArrayList<material> ma = new ArrayList<material>();
    int[] ma_num= new int[5];
    equip [] eq= new equip[7];
    String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩",
            "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏",
            "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚",
            "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳",
            "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗",
            "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余",
            "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁",
            "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞",
            "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季", "麻",
            "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐",
            "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝",
            "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓",
            "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢",
            "滑", "裴", "陆", "荣", "翁", "荀", "羊", "于", "惠", "甄", "曲", "家", "封", "芮", "羿",
            "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧",
            "隗", "山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫",
            "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武", "符", "刘", "景", "詹", "束",
            "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "溥", "印", "宿", "白", "怀", "蒲", "邰",
            "从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "郁", "胥",
            "能", "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵",
            "冉", "宰", "郦", "雍", "却", "璩", "桑", "桂", "濮", "牛", "寿", "通", "边", "扈", "燕",
            "冀", "浦", "尚", "农", "温", "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹",
            "习", "宦", "艾", "鱼", "容", "向", "古", "易", "慎", "戈", "廖", "庾", "终", "暨", "居",
            "衡", "步", "都", "耿", "满", "弘", "匡", "国", "文", "寇", "广", "禄", "阙", "东", "欧",
            "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂", "晁", "勾", "敖", "融",
            "冷", "訾", "辛", "阚", "那", "简", "饶", "空", "曾", "毋", "沙", "乜", "养", "鞠", "须",
            "丰", "巢", "关", "蒯", "相", "查", "后", "荆", "红", "游", "郏", "竺", "权", "逯", "盖",
            "益", "桓", "公", "仉", "督", "岳", "帅", "缑", "亢", "况", "郈", "有", "琴", "归"};
    public hero()//随机生成中文名
    {
        Random r = new Random();
        int index = r.nextInt(Surname.length);
        name =Surname[index];
        int index2 = r.nextInt(Surname.length);
        name = name + Surname[index2];
        int index3 = r.nextInt(Surname.length);
        name = name + Surname[index3];
        power=(int)cal_power();
        exp=10;
        money=0;
    }
    abstract double cal_power() ;
    public double cal_exp(){
        if(exp<2000) {
            exp*=1.7;
        }else
            exp=(int)(exp*(Math.random()*0.2+1));
        return exp;
    }
    public void cal_material() {
        int size = ma.size();
        for (int i = 0;i<size;i++) {
            if(ma.get(i).id==0) {
                ma_num[0]++;
            }else if(ma.get(i).id==1) {
                ma_num[1]++;
            }else if(ma.get(i).id==2) {
                ma_num[2]++;
            }else if(ma.get(i).id==3) {
                ma_num[3]++;
            }else if(ma.get(i).id==4) {
                ma_num[4]++;
            }
            ma.get(i).id=-1;
        }
    }
    public void up_equip(int i,JFrame frame,chuanqi_pro c) {//升级并扣除材料
        if(money>=1000) {
            if(ma_num[0]>1 && ma_num[1]>1 && ma_num[2]>1) {
                eq[i].up(i,this);
                ma_num[0]-=2;
                ma_num[1]-=2;
                ma_num[2]-=2;
                money-=1000;
            }else if(ma_num[0]+ma_num[1]+ma_num[2]+ma_num[3]>=6) {
                int temp0,temp1,temp2,temp3;
                if(ma_num[0]>2)
                    temp0=2;
                else
                    temp0=ma_num[0];
                if(ma_num[1]>2)
                    temp1=2;
                else
                    temp1=ma_num[1];
                if(ma_num[2]>2)
                    temp2=2;
                else
                    temp2=ma_num[2];
                temp3=6-temp0-temp1-temp2;
                if(temp3<=ma_num[3]) {
                    eq[i].up(i,this);
                    money-=1000;
                    ma_num[0]-=temp0;
                    ma_num[1]-=temp1;
                    ma_num[2]-=temp2;
                    ma_num[3]-=temp3;
                }else {
                    c.tips("材料不足",frame);
                    System.out.println("材料不足");
                    System.out.println();
                }
            }else {
                c.tips("材料不足",frame);
                System.out.println("材料不足");
                System.out.println();
            }
        }else {
            c.tips("金币不足",frame);
            System.out.println("金币不足");
            System.out.println();
        }
    }
    public String uplevel() {
        boolean f=false;
        while (exp_temp>=exp) {
            double num = Math.abs(exp-exp_temp);
            level++;
            exp=cal_exp();
            exp_temp=num;
            attack+=5;
            defense+=5;
            maxlife+=5;
            magic+=5;
            f=true;
        }
        if(f==true) {
            life=maxlife;
            return "恭喜，升级了，目前"+level+"级";
        }
        else {
            return "经验尚不足，还差"+Math.abs(exp-exp_temp)+"点，目前"+level+"级";
        }
    }
    public void highattack(monster m) {
        attack*=(2+skill[2]);//默认两倍暴击
        attack(m);
        attack/=(2+skill[2]);
    }
    public void attack(hero h) {}//不用
    public void highmagic(monster m) {
        magic*=(2+skill[2]);
        magic(m);
        magic/=(2+skill[2]);
    }
    public void magic(hero h) {}//不用
    public void life() {
        life+=maxlife*0.3;
        check();
    }
    public void defense() {
        defense*=1.5;
    }
    public boolean escape() {
        money=(int)(money*0.1);
        System.out.println("您还剩"+money+"个金币");
        return false;
    }
    public void show(JTextArea text){
        text.append(name
                +System.lineSeparator());
        text.append("攻击力："+attack
                +System.lineSeparator());
        text.append("防御力："+defense
                +System.lineSeparator());
        text.append("当前生命值："+life
                +System.lineSeparator());
        text.append("最大生命值："+maxlife
                +System.lineSeparator());
        text.append("法力值："+magic
                +System.lineSeparator());
        text.append("当前等级："+level
                +System.lineSeparator());
        text.append("当前经验："+exp_temp
                +System.lineSeparator());
        text.append("尚缺经验："+Math.abs(exp-exp_temp)
                +System.lineSeparator());
        text.append("晕眩率："+(int)(skill[0]*100)+"%"
                +System.lineSeparator());
        text.append("暴击率："+(int)(skill[1]*100)+"%"
                +System.lineSeparator());
        text.append("暴击效果加成："+(int)(skill[2]*100)+"%"
                +System.lineSeparator());
        text.append("破防加成："+(int)(skill[3]*100)+"%"
                +System.lineSeparator());
        text.append("伤害加成："+(int)(skill[4]*100)+"%"
                +System.lineSeparator());
        text.append("名刀触发概率："+(int)(skill[5]*100)+"%"
                +System.lineSeparator());
        text.append("复活甲概率："+(int)(skill[6]*100)+"%"
                +System.lineSeparator());
        text.append("永夜回复比例："+(int)(skill[7]*100)+"%,当生命不大于30%时恢复一定比例"
                +System.lineSeparator());
        text.append("免伤比例："+(int)(skill[8]*100)+"%"
                +System.lineSeparator());
        text.append("闪避率："+(int)(skill[9]*100)+"%"
                +System.lineSeparator());
        text.append("战力值："+power
                +System.lineSeparator());
        text.append(System.lineSeparator());
    }
    public void show_material(JTextArea text) {
        text.append("您目前拥有" + System.lineSeparator());
        text.append("木材:" + ma_num[0] + "个" + System.lineSeparator());
        text.append("强化石:" + ma_num[1] + "个" + System.lineSeparator());
        text.append("圣水:" + ma_num[2] + "个" + System.lineSeparator());
        text.append("万能石:" + ma_num[3] + "个" + System.lineSeparator());
        text.append("洗炼石:" + ma_num[4] + "个" + System.lineSeparator());
        text.append(System.lineSeparator());
    }
    public void show_equip(JTextArea text) {
        text.append("您目前的装备有：" + System.lineSeparator());
        for (int i = 0; i < 7; i++) {
            if (eq[i] != null) {
                if (i == 0) {
                    text.append("帽子" + System.lineSeparator());
                }
                if (i == 1) {
                    text.append("武器" + System.lineSeparator());
                }
                if (i == 2) {
                    text.append("衣服" + System.lineSeparator());
                }
                if (i == 3) {
                    text.append("裤子" + System.lineSeparator());
                }
                if (i == 4) {
                    text.append("鞋子" + System.lineSeparator());
                }
                if (i == 5) {
                    text.append("项链" + System.lineSeparator());
                }
                if (i == 6) {
                    text.append("戒指" + System.lineSeparator());
                }
                eq[i].show(text);
            }
        }
        text.append(System.lineSeparator());
    }
    public void show_money(JTextArea text) {
        text.append("您当前有" + money + "个金币" + System.lineSeparator());
        text.append(System.lineSeparator());
    }
    public void compare(monster m) {
        if(this.power >m.power)
            System.out.println("您的战力大于怪兽");
        else
            System.out.println("您的战力不大于怪兽");
        System.out.println();
    }
    public void original_equipment(equip eq,JTextArea text,JTextArea text2,JLabel l1,JLabel l2,int a) {
        if(this.eq[eq.id]==null) {
            this.eq[eq.id]=eq;
            if(eq.id==0||eq.id==2||eq.id==3||eq.id==4) {
                defense=defense+this.eq[eq.id].defense;
                maxlife=maxlife+this.eq[eq.id].maxlife;
                life=maxlife;
            }else if(eq.id==1) {
                attack=attack+this.eq[eq.id].attack;
            }else if(eq.id==5||eq.id==6) {
                magic=magic+this.eq[eq.id].magic;
            }
            if(eq.id==0) {
                skill[0]+=this.eq[eq.id].skill[0];
            }else if(eq.id==1) {
                skill[1]+=this.eq[eq.id].skill[1];
                skill[2]+=this.eq[eq.id].skill[2];
            }else if(eq.id==2) {
                skill[5]+=this.eq[eq.id].skill[5];
                skill[8]+=this.eq[eq.id].skill[8];
            }else if(eq.id==3) {
                skill[6]+=this.eq[eq.id].skill[6];
            }else if(eq.id==4) {
                skill[7]+=this.eq[eq.id].skill[7];
                skill[9]+=this.eq[eq.id].skill[9];
            }else if(eq.id==5) {
                skill[3]+=this.eq[eq.id].skill[3];
            }else if(eq.id==6) {
                skill[4]+=this.eq[eq.id].skill[4];
            }
            power=(int)cal_power();
            text.append("自动装备"+System.lineSeparator());
            text.append(System.lineSeparator());
            text2.append("自动装备"+System.lineSeparator());
            text2.append(System.lineSeparator());
            System.out.println("自动装备");
        }else if(eq.id==0 && eq.defense>this.eq[eq.id].defense && eq.maxlife>this.eq[eq.id].maxlife
                && eq.quality_level>this.eq[eq.id].quality_level && eq.skill[0]>this.eq[eq.id].skill[0]) {
            skill[0]-=this.eq[eq.id].skill[0];

            defense=defense-this.eq[eq.id].defense;
            maxlife=maxlife-this.eq[eq.id].maxlife;
            this.eq[eq.id]=eq;
            defense=defense+this.eq[eq.id].defense;
            maxlife=maxlife+this.eq[eq.id].maxlife;
            life=maxlife;

            skill[0]+=this.eq[eq.id].skill[0];

            power=(int)cal_power();
            text.append("自动替换"+System.lineSeparator());
            text.append(System.lineSeparator());
            text2.append("自动替换"+System.lineSeparator());
            text2.append(System.lineSeparator());
            System.out.println("自动替换");
        }else if(eq.id==1 && eq.attack>this.eq[eq.id].attack
                && eq.quality_level>this.eq[eq.id].quality_level && eq.skill[1]>this.eq[eq.id].skill[1]
                && eq.skill[2]>this.eq[eq.id].skill[2]) {
            skill[1]-=this.eq[eq.id].skill[1];
            skill[2]-=this.eq[eq.id].skill[2];

            attack=attack-this.eq[eq.id].attack;
            this.eq[eq.id]=eq;
            attack=attack+this.eq[eq.id].attack;

            skill[1]+=this.eq[eq.id].skill[1];
            skill[2]+=this.eq[eq.id].skill[2];

            power=(int)cal_power();
            text.append("自动替换"+System.lineSeparator());
            text.append(System.lineSeparator());
            text2.append("自动替换"+System.lineSeparator());
            text2.append(System.lineSeparator());
            System.out.println("自动替换");
        }else if(eq.id==2 && eq.defense>this.eq[eq.id].defense && eq.maxlife>this.eq[eq.id].maxlife
                && eq.quality_level>this.eq[eq.id].quality_level && eq.skill[5]>this.eq[eq.id].skill[5]
                && eq.skill[8]>this.eq[eq.id].skill[8]) {
            skill[5]-=this.eq[eq.id].skill[5];
            skill[8]-=this.eq[eq.id].skill[8];

            defense=defense-this.eq[eq.id].defense;
            maxlife=maxlife-this.eq[eq.id].maxlife;
            this.eq[eq.id]=eq;
            defense=defense+this.eq[eq.id].defense;
            maxlife=maxlife+this.eq[eq.id].maxlife;
            life=maxlife;

            skill[5]+=this.eq[eq.id].skill[5];
            skill[8]+=this.eq[eq.id].skill[8];

            power=(int)cal_power();
            text.append("自动替换"+System.lineSeparator());
            text.append(System.lineSeparator());
            text2.append("自动替换"+System.lineSeparator());
            text2.append(System.lineSeparator());
            System.out.println("自动替换");
        }else if(eq.id==3 && eq.defense>this.eq[eq.id].defense && eq.maxlife>this.eq[eq.id].maxlife
                && eq.quality_level>this.eq[eq.id].quality_level && eq.skill[6]>this.eq[eq.id].skill[6]) {
            skill[6]-=this.eq[eq.id].skill[6];

            defense=defense-this.eq[eq.id].defense;
            maxlife=maxlife-this.eq[eq.id].maxlife;
            this.eq[eq.id]=eq;
            defense=defense+this.eq[eq.id].defense;
            maxlife=maxlife+this.eq[eq.id].maxlife;
            life=maxlife;

            skill[6]+=this.eq[eq.id].skill[6];

            power=(int)cal_power();
            text.append("自动替换"+System.lineSeparator());
            text.append(System.lineSeparator());
            text2.append("自动替换"+System.lineSeparator());
            text2.append(System.lineSeparator());
            System.out.println("自动替换");
        }else if(eq.id==4 && eq.defense>this.eq[eq.id].defense && eq.maxlife>this.eq[eq.id].maxlife
                && eq.quality_level>this.eq[eq.id].quality_level && eq.skill[7]>this.eq[eq.id].skill[7]
                && eq.skill[9]>this.eq[eq.id].skill[9]) {
            skill[7]-=this.eq[eq.id].skill[7];
            skill[9]-=this.eq[eq.id].skill[9];

            defense=defense-this.eq[eq.id].defense;
            maxlife=maxlife-this.eq[eq.id].maxlife;
            this.eq[eq.id]=eq;
            defense=defense+this.eq[eq.id].defense;
            maxlife=maxlife+this.eq[eq.id].maxlife;
            life=maxlife;

            skill[7]+=this.eq[eq.id].skill[7];
            skill[9]+=this.eq[eq.id].skill[9];

            power=(int)cal_power();
            text.append("自动替换"+System.lineSeparator());
            text.append(System.lineSeparator());
            text2.append("自动替换"+System.lineSeparator());
            text2.append(System.lineSeparator());
            System.out.println("自动替换");
        }else if(eq.id==5 && eq.magic>this.eq[eq.id].magic
                && eq.quality_level>this.eq[eq.id].quality_level && eq.skill[3]>this.eq[eq.id].skill[3]) {
            skill[3]-=this.eq[eq.id].skill[3];

            magic=magic-this.eq[eq.id].magic;
            this.eq[eq.id]=eq;
            magic=magic+this.eq[eq.id].magic;

            skill[3]+=this.eq[eq.id].skill[3];

            power=(int)cal_power();
            text.append("自动替换"+System.lineSeparator());
            text.append(System.lineSeparator());
            text2.append("自动替换"+System.lineSeparator());
            text2.append(System.lineSeparator());
            System.out.println("自动替换");
        }else if(eq.id==6 && eq.magic>this.eq[eq.id].magic
                && eq.quality_level>this.eq[eq.id].quality_level && eq.skill[4]>this.eq[eq.id].skill[4]) {
            skill[4]-=this.eq[eq.id].skill[4];

            magic=magic-this.eq[eq.id].magic;
            this.eq[eq.id]=eq;
            magic=magic+this.eq[eq.id].magic;

            skill[4]+=this.eq[eq.id].skill[4];

            power=(int)cal_power();
            text.append("自动替换"+System.lineSeparator());
            text.append(System.lineSeparator());
            text2.append("自动替换"+System.lineSeparator());
            text2.append(System.lineSeparator());
            System.out.println("自动替换");
        }else {
            System.out.println("原装备属性：");
            System.out.println("是否替换？不替换-0，替换-1");
            if (a==1) {
                if(eq.id==0) {
                    skill[0]-=this.eq[eq.id].skill[0];
                }else if(eq.id==1) {
                    skill[1]-=this.eq[eq.id].skill[1];
                    skill[2]-=this.eq[eq.id].skill[2];
                }else if(eq.id==2) {
                    skill[5]-=this.eq[eq.id].skill[5];
                    skill[8]-=this.eq[eq.id].skill[8];
                }else if(eq.id==3) {
                    skill[6]-=this.eq[eq.id].skill[6];
                }else if(eq.id==4) {
                    skill[7]-=this.eq[eq.id].skill[7];
                    skill[9]-=this.eq[eq.id].skill[9];
                }else if(eq.id==5) {
                    skill[3]-=this.eq[eq.id].skill[3];
                }else if(eq.id==6) {
                    skill[4]-=this.eq[eq.id].skill[4];
                }

                if(eq.id==0||eq.id==2||eq.id==3||eq.id==4) {
                    defense=defense-this.eq[eq.id].defense;
                    maxlife=maxlife-this.eq[eq.id].maxlife;
                    this.eq[eq.id]=eq;
                    defense=defense+this.eq[eq.id].defense;
                    maxlife=maxlife+this.eq[eq.id].maxlife;
                    life=maxlife;
                }else if(eq.id==1) {
                    attack=attack-this.eq[eq.id].attack;
                    this.eq[eq.id]=eq;
                    attack=attack+this.eq[eq.id].attack;
                }else if(eq.id==5||eq.id==6) {
                    magic=magic-this.eq[eq.id].magic;
                    this.eq[eq.id]=eq;
                    magic=magic+this.eq[eq.id].magic;
                }

                if(eq.id==0) {
                    skill[0]+=this.eq[eq.id].skill[0];
                }else if(eq.id==1) {
                    skill[1]+=this.eq[eq.id].skill[1];
                    skill[2]+=this.eq[eq.id].skill[2];
                }else if(eq.id==2) {
                    skill[5]+=this.eq[eq.id].skill[5];
                    skill[8]+=this.eq[eq.id].skill[8];
                }else if(eq.id==3) {
                    skill[6]+=this.eq[eq.id].skill[6];
                }else if(eq.id==4) {
                    skill[7]+=this.eq[eq.id].skill[7];
                    skill[9]+=this.eq[eq.id].skill[9];
                }else if(eq.id==5) {
                    skill[3]+=this.eq[eq.id].skill[3];
                }else if(eq.id==6) {
                    skill[4]+=this.eq[eq.id].skill[4];
                }
                power=(int)cal_power();
                text.append(System.lineSeparator());
                text2.append(System.lineSeparator());
            }
        }
    }
    public void check() {
        if(life>maxlife)
            life=maxlife;
    }
    public void death() {
        for(int i = 0;i<7;i++) {
            if(eq[i]!=null) {
                this.attack-=eq[i].attack;
                this.defense-=eq[i].defense;
                this.maxlife-=eq[i].maxlife;
                this.magic-=eq[i].magic;
            }
        }
        skill[0]=0;
        skill[1]=0;
        skill[2]=0;
        skill[3]=0;
        skill[4]=0;
        skill[5]=0;
        skill[6]=0;
        skill[7]=0;
        skill[8]=0;
        skill[9]=0;
        Arrays.fill(eq, null);
        ma.clear();
        for(int i =0;i<5;i++) {
            ma_num[i]=0;
        }
        money=0;
        life=maxlife;
        power=(int)cal_power();
        System.out.println();
    }
}