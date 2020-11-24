package com.ecnu.redo.core;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

import java.util.*;

public class GeneticAlgorithm {
    public static int[] mutation(int arr[], double pro) {
        double r = Math.random();
        if (r < pro) {
            Random ran = new Random();
            int temp2 = ran.nextInt(arr.length);
            if (arr[temp2] == 1) {
                arr[temp2] = 0;
            } else
                arr[temp2] = 1;
        }
        return arr;
    }

    public static ArrayList<int[]> crossover(ArrayList<int[]> group, int sum, double A[][]) {
        HashSet<int[]> nset = new HashSet<int[]>();
        double mut_pro = 0.1;
        //先交叉在变异
        while (nset.size() < sum) {
            //交叉
            //任意选择两组
            int num[] = new int[2];
            Random ran = new Random();
            int temp = ran.nextInt(group.size());
            num[0] = temp;
            while (num.length < 2) {
                temp = ran.nextInt(group.size());
                if (temp != num[0])
                    num[1] = temp;
            }
            int arr1[] = group.get(num[0]);
            int arr2[] = group.get(num[1]);
            //pmx 两点交叉 随机两点
            int point[] = new int[2];
            ran = new Random();
            temp = ran.nextInt(arr1.length);
            point[0] = temp;
            while (point.length < 2) {
                temp = ran.nextInt(arr1.length);
                if (temp != point[0])
                    num[1] = temp;
            }
            int son1[] = new int[arr1.length];
            int son2[] = new int[arr1.length];
            //开始交叉
            for (int i = 0; i < arr1.length; i++) {
                if ((i < point[0]) || (i >= point[1])) {
                    son1[i] = arr1[i];
                    son2[i] = arr2[i];
                } else if ((i >= point[0]) && (i < point[1])) {
                    son1[i] = arr2[i];
                    son2[i] = arr1[i];
                }
            }
            //变异变异概率为0.1
            son1 = mutation(son1, mut_pro);
            son2 = mutation(son2, mut_pro);


            ArrayList<Integer> al2 = new ArrayList<Integer>();

            //检查重复性
            boolean bl = true;
            for (int[] is : nset) {
                if (Arrays.equals(son1, is)) {
                    bl = false;
                    break;
                }
            }
            if (bl)
                nset.add(son1);
            bl = true;
            if (nset.size() >= sum) break;
            for (int[] is : nset) {
                if (Arrays.equals(son2, is)) {
                    bl = false;
                    break;
                }
            }
            if (bl)
                nset.add(son2);
        }
        //将set转化为al
        ArrayList<int[]> ngro = new ArrayList<int[]>();
        for (int[] is : nset) {
            ngro.add(is);
        }
        return ngro;
    }

    public static ArrayList<Object> select(ArrayList<int[]> group, ArrayList<Double> complexity,
                                           double min_com, int sum, int high) {//high:优选子代个数，sum 样本容量
        ArrayList<Object> al = new ArrayList<Object>();
        ArrayList<int[]> ngro = new ArrayList<int[]>();
        //这里我们让复杂度倒数作为适应度
        double pro[] = new double[group.size()]; // 各染色体选择概率
        double pro_ac[] = new double[group.size()]; // 累计概率
        int[] sche = new int[group.get(0).length];
        ArrayList<Double> fitness = new ArrayList<Double>();
        double value = 0;
        for (int i = 0; i < group.size(); i++) {
            if (complexity.get(i) < min_com) {
                min_com = complexity.get(i);
                sche = group.get(i);//更新最小值
            }
            double temp = 1.0 / complexity.get(i);//待调整
            fitness.add(temp);
            value += temp;
        }
        for (int i = 0; i < group.size(); i++) {
            pro[i] = fitness.get(i) / value;
            if (i == 0) {
                pro_ac[i] = pro[i];
            } else
                pro_ac[i] = pro_ac[i - 1] + pro[i];

        }
        pro_ac[group.size() - 1] = 1;

        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < high) {
            double r = Math.random();
            int temp = 0;
            for (int j = 0; j < sum; j++) {
                if (r >= pro_ac[j]) {
                    temp++;
                } else
                    break;
            }
            set.add(temp);
        }
        for (Integer it : set) {
            ngro.add(group.get(it));
        }
        al.add(ngro);
        al.add(min_com);
        al.add(sche);
        return al;
    }

    public static double compute(ArrayList al, int[] group, int tag, double ca[][]) {

        ArrayList<Integer> alt = new ArrayList<Integer>();
        alt = assemAlt(group, al, tag);
        double[][] tem_mat = new double[alt.size()][alt.size()];
        tem_mat = initial_M(alt.size());
        for (int l = 0; l < alt.size(); l++) {
            for (int ll = 0; ll < alt.size(); ll++) {
                //System.out.println("l ll:"+l+" "+ll);
                int t1 = alt.get(l);
                int t2 = alt.get(ll);
                tem_mat[l][ll] = ca[t1][t2];
            }
        }

        double temp_total = cal_alpha(tem_mat) + cal_data(tem_mat);
        //生成新的矩阵
        double[][] temMatrix = initial_M(ca.length - alt.size() + 1);//初始化为0,矩阵大小需要调整，将temMatrix赋值给ca
        //新建一个标记数组
        int[] tagNum = new int[ca.length];
        int lt = 0;
        for (int k = 0; (lt < ca.length) && (k < alt.size()); lt++) {
            if (lt != alt.get(k)) {
                tagNum[lt] = 0;//0表示不在alt购件中
            } else {
                tagNum[lt] = 1;//假如构件已在alt构件中被计算过一次，标记为1，包括tag和tag相连接的构件
                k++;
            }
        }
        for (; lt < ca.length; lt++) {
            tagNum[lt] = 0;
        }
		/*for (int i = 0; i < tagNum.length; i++) {
			System.out.println(tagNum[i]);
		}*/
        //相关的复制
        temMatrix[0][0] = temp_total;
        int[] temNum = new int[ca.length - alt.size() + 1];//创建新构件对应的下标数组
        System.out.println(alt);

        /*System.out.println(temNum.length);*/
        for (int l = 1, ll = 0; l < temNum.length; ll++) {//tem[0]表示新构件
            if (tagNum[ll] == 0) {//ll表示遍历一遍原ca的标记数组，l表示为新的temNum数组所对应的原ca中下标数
                temNum[l] = ll;
                l++;
				/*System.out.println("l:"+l);
				System.out.println("ll:"+ll);*/
            }
        }
        for (int l = 1; l < temNum.length; l++) {
            double temp = 0;
            double temp1 = 0;
            for (int ll = 0; ll < alt.size(); ll++) {
                temp += ca[alt.get(ll)][temNum[l]];
                //System.out.println(ca[al.get(ll)][temNum[l]]);
                temp1 += ca[temNum[l]][alt.get(ll)];
                //System.out.println(ca[temNum[l]][al.get(ll)]);
            }
            temMatrix[0][l] = temp;
            temMatrix[l][0] = temp1;
        }
        //不相关的复制
        for (int l = 1; l < temNum.length; l++) {//l为原ca的变量，k为新购件集合al的变量，ll为新的矩阵temMatrix的变量
            for (int ll = 1; ll < temNum.length; ll++) {
                temMatrix[l][ll] = ca[temNum[l]][temNum[ll]];
            }
        }
        double complexity = cal_alpha(temMatrix) + cal_data(temMatrix);
        return complexity;
    }

    public static int[] ran_sch(int len) {//随机生成一个串，长为len

        int arr[] = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Math.random() > 0.5 ? 1 : 0;
        }
        return arr;
    }

    public static ArrayList<int[]> ran_gen(int len, int sum) {

        int total = 0;
        int constr = sum * 1000;//最大边界
        ArrayList<int[]> al = new ArrayList<int[]>();
        for (; (al.size() < sum) && (total < constr); total++) {//基因库被填满或者超过一个遍历次数，防止无穷无尽遍历下去
            //随机产生串
            int tem[] = ran_sch(len);
            boolean jud = true;
            //去重
            for (int i = 0; i < al.size(); i++) {
                if (Arrays.equals(tem, al.get(i))) {
                    jud = false;
                    break;
                }
            }
            if (jud) {
                al.add(tem);
            }
        }
        return al;
    }

    //static double A[][] = new double[100][100];
    public static int[] cal_cons(double A[][]) {//计算每个构件连接关系相关的构件数，返回一个数组
        int cons[] = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            cons[i] = cal_each_con(i, A);
        }
        return cons;
    }

    public static int cal_each_con(int c, double A[][]) {//计算每个构件关联的构件数
        int sum = 0;
        //计算行
        for (int i = 0; i < A.length; i++) {
            if (c != i) {
                sum += A[c][i];
            }
        }
        //计算lie
        for (int i = 0; i < A.length; i++) {
            if (c != i) {
                sum += A[i][c];
            }
        }
        return sum;
    }

    public static ArrayList<Integer> getConNum(int tag, double A[][]) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 0; i < A.length; i++) {
            if (i != tag) {
                if ((A[tag][i] != 0) || (A[i][tag] != 0)) {
                    al.add(i);
                }
            }
        }
        return al;
    }

    public static double cal_singular(Matrix A) {//计算特征值绝对值
        SingularValueDecomposition s = A.svd();
        Matrix svalues = new Matrix(s.getSingularValues(), 1);
        //svalues.print(9, 6);
        double tot = 0;
        for (int i = 0; i < s.rank(); i++) {
            double temp = (double) svalues.get(0, i);
            tot += Math.abs(temp);
        }

        return tot;
    }

    public static double[][] initial_M(int len) {//初始化矩阵
        double A[][] = new double[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                A[i][j] = 0;
            }
        }
        return A;
    }

    // todo
    public static double cal_alpha(double data[][]) {
        double temp_total = 0;
        for (int k1 = 0; k1 < data.length; k1++) {
            temp_total += data[k1][k1];
        }
        return temp_total;
    }

    // todo
    public static double cal_data(double data[][]) {
        double complxity = 0;
        double con[][] = initial_M(data.length);//初始化n维连接矩阵
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if ((data[i][j] != 0) && (i != j)) {
                    con[i][j] = 1;
                }
            }
        }
        Matrix ATemp = Matrix.constructWithCopy(con);
        complxity = sum(data) * cal_singular(ATemp) / data.length;
        return complxity;
    }

    public static double sum(double data[][]) {//计算 贝塔 的和
        double s = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (i != j)
                    s = s + data[i][j];
            }
        }
        //System.out.println("b = " + s);
        return s;
    }

    public static String[] assemSave(ArrayList<Integer> al, int[] temNum, String[] assemMatrix) {
        String[] assemTM = new String[temNum.length];
        String tt = "(";
        for (int l = 0; l < al.size(); l++) {
            if (tt == "(") {
                tt += assemMatrix[al.get(l)];
            } else {
                tt += "," + assemMatrix[al.get(l)];
            }
        }
        tt += ")";
        assemTM[0] = tt;
        for (int l = 1; l < temNum.length; l++) {
            assemTM[l] = assemMatrix[temNum[l]];
        }
        return assemTM;
    }

    public static ArrayList assemAlt(int[] group, ArrayList al, int tag) {
        ArrayList alt = new ArrayList();
        alt.add(tag);
        for (int j = 0; j < al.size(); j++) {
            if (group[j] == 0) {
                alt.add(al.get(j));//如果为0，则添加al中的该元素
            }
        }
        return alt;
    }

    // 顶层
    public static String refactor(double A[][]) {


		/*PrintStream ps = new PrintStream("D:\\a.txt");
		 System.setOut(ps);*/
		 /*double A[][]={{3,1,0,0,0,0,0,0,0,0,0.5,0,0},
					{1,4,1,0,0,0,0,0,0,2,0,0,0},
					{0,1,4,0.5,0,0,0.5,1,0,0,0,0,0},
					{0,0,0.5,1,0.5,0,0,0,0,0,0,0,0},
					{0,0,0,0.5,2,0.5,0,0,0,0,0,0,0},
					{0,0,0,0,0.5,1,0,0,0,0,0,0,0},
					{0,0,0.5,0,0,0,2,0,0,0,0,0,0},
					{0,0,1,0,0,0,0,2,0.5,0,0,1,0.5},
					{0,0,0,0,0,0,0,0.5,4,0,0,0,0},
					{0,2,0,0,0,0,0,0,0,3,0,0,0},
					{0.5,0,0,0,0,0,0,0,0,0,1,0.5,0},
					{0,0,0,0,0,0,0,1,0,0,0.5,4,0},
					{0,0,0,0,0,0,0,0.5,0,0,0,0,1}
					};*/
		/* double A[][] = new double[100][100];
			for(int l=0;l<A.length;l++){
				for(int ll=0;ll<A.length;ll++){
					int rd=1;
					A[l][ll] = rd * Math.random()*5;
				}
			}*/
        double ca[][] = A;
        //一次遍历
        String[] assemMatrix = new String[A.length];
        for (int i = 0; i < A.length; i++) {
            assemMatrix[i] = i + "";
        }
        double minCom = cal_alpha(ca) + cal_data(ca);
        while (ca.length > 3) {
            double thisCom = minCom;
            int conNum[] = cal_cons(ca);
            int maxCon = conNum[0];
            int tag = 0;
            for (int i = 1; i < ca.length; i++) {
                if (conNum[i] > maxCon) {
                    tag = i;
                    maxCon = conNum[i];
                }
            }//获取最大的一个connection complexity的构件作为启发信息，以及它的下标数tag
            //获取相连接构件的序号
            ArrayList<Integer> al = getConNum(tag, ca);//tag相互连接的构件
            //遍历所有组合，筛选出局部最小，其中，tag为目标中心构件，在随机加上al中的构件个数，一种采用全搜索，一种可以考虑使用遗传算法
            if (al.size() < 17) {//选择使用遗传算法的规模
                ArrayList<Integer> alt = new ArrayList();//alt为用于遍历的数组

                //遍历全组数，初始化
                //建立一个数组， 0-与tag合并为一个构件 1-单独
                int[] group = new int[al.size()];
                for (int j = 0; j < al.size(); j++) {
                    group[j] = 0;
                }
                //组成alt
                boolean jud = true;
                String[] assemMatrix1 = assemMatrix;

                double[][] layerTempMatrix = initial_M(ca.length - al.size() + 1);
                while (jud) {
                    //输出或复杂度计算和比较
                    alt = assemAlt(group, al, tag);
                    double[][] tem_mat = new double[alt.size()][alt.size()];
                    tem_mat = initial_M(alt.size());
                    for (int l = 0; l < alt.size(); l++) {
                        for (int ll = 0; ll < alt.size(); ll++) {
                            int t1 = alt.get(l);
                            int t2 = alt.get(ll);
                            tem_mat[l][ll] = ca[t1][t2];
                        }
                    }

                    double temp_total = cal_alpha(tem_mat) + cal_data(tem_mat);
                    //生成新的矩阵
                    double[][] temMatrix = initial_M(ca.length - alt.size() + 1);//初始化为0,矩阵大小需要调整，将temMatrix赋值给ca
                    //新建一个标记数组
                    Collections.sort(alt);
                    int[] tagNum = new int[ca.length];
                    int lt = 0;
                    for (int k = 0; (lt < ca.length) && (k < alt.size()); lt++) {
                        if (lt != alt.get(k)) {
                            tagNum[lt] = 0;//0表示不在alt构件中，是新系统的子构件
                        } else {
                            tagNum[lt] = 1;//1表示为在alt中是alt构件的子构件
                            k++;
                        }
                    }
                    for (; lt < ca.length; lt++) {
                        tagNum[lt] = 0;
                    }
                    //相关的复制
                    temMatrix[0][0] = temp_total;
                    int[] temNum = new int[ca.length - alt.size() + 1];//创建新构件对应的下标数组
                    for (int l = 1, ll = 0; l < temNum.length; ll++) {//tem[0]表示新构件
                        if (tagNum[ll] == 0) {//ll表示遍历一遍原ca的标记数组，l表示为新的temNum数组所对应的原ca中下标数
                            temNum[l] = ll;
                            l++;
                        }
                    }
                    for (int l = 1; l < temNum.length; l++) {
                        double temp = 0;
                        double temp1 = 0;
                        for (int ll = 0; ll < alt.size(); ll++) {
                            temp += ca[alt.get(ll)][temNum[l]];
                            temp1 += ca[temNum[l]][alt.get(ll)];
                        }
                        temMatrix[0][l] = temp;
                        temMatrix[l][0] = temp1;
                    }
                    //不相关的复制
                    for (int l = 1; l < temNum.length; l++) {//l为原ca的变量，k为新购件集合al的变量，ll为新的矩阵temMatrix的变量
                        for (int ll = 1; ll < temNum.length; ll++) {
                            temMatrix[l][ll] = ca[temNum[l]][temNum[ll]];
                        }
                    }
                    //输出第二个矩阵，暂时隐藏

                    double complexity = cal_alpha(temMatrix) + cal_data(temMatrix);
                    if (complexity < minCom) {
                        minCom = complexity;
                        assemMatrix1 = assemSave(alt, temNum, assemMatrix);
                        layerTempMatrix = new double[temMatrix.length][temMatrix.length];
                        layerTempMatrix = temMatrix;
                    }

                    alt.clear();
                    //做加法，然后规则数组
                    group[al.size() - 1] += 1;
                    for (int j = al.size() - 1; j >= 0; j--) {
                        if (group[j] == 2) {
                            if (j == 0) {
                                jud = false;
                                break;
                            } else {
                                group[j] = 0;
                                group[j - 1] += 1;
                            }
                        } else
                            break;
                    }
                }
                assemMatrix = assemMatrix1;
                ca = new double[layerTempMatrix.length][layerTempMatrix.length];
                ca = layerTempMatrix;
                if (thisCom == minCom) {
                    break;
                }
            } else {//超过代数部分使用遗传
                //遗传算法，代数300，样本容量100
                //可使用参数 tag：最大的关联构件序号，maxCon，相关联构件的最大连接数无用；al：相连接的构件数
                int[] bestson = new int[al.size()];
                int sum = 100;//基因库大小
                int maxGen = 300;
                int high = sum / 10;//优秀子代个数

                ArrayList<int[]> group = ran_gen(al.size(), sum);
                ArrayList<Double> complexity = new ArrayList<Double>();
                double min_com = minCom;
                for (int numGen = 0; numGen < maxGen; numGen++) {
                    //计算适应度，保存在comple中，两个arraylist一一对应
                    //计算复杂度
                    complexity.clear();
                    for (int i = 0; i < group.size(); i++) {
                        double comTem = compute(al, group.get(i), tag, ca);
                        complexity.add(comTem);
                    }
                    //根据复杂度计算适应度
                    //选择
                    ArrayList<Object> ob = select(group, complexity, min_com, sum, high);
                    //处理返回值
                    group = (ArrayList<int[]>) ob.get(0);
                    double ncom = (double) ob.get(1);
                    if (ncom < min_com) {
                        min_com = ncom;
                        //更新代数，让多少代不变化停止
                        bestson = (int[]) ob.get(2);
                    }
                    //交叉：两点交叉//变异:概率0.1
                    group = crossover(group, sum, ca);

                }
                //把bestson 和 min_com进行转化
                if (min_com == minCom) {
                    break;
                }
                //组装 assem阵型和最小复杂度
                minCom = min_com;
                ArrayList<Integer> alt = assemAlt(bestson, al, tag);
                double[][] temMatrix = initial_M(ca.length - alt.size() + 1);//初始化为0,矩阵大小需要调整，将temMatrix赋值给ca
                //新建一个标记数组
                int[] tagNum = new int[ca.length];
                int lt = 0;
                for (int k = 0; (lt < ca.length) && (k < alt.size()); lt++) {
                    if (lt != alt.get(k)) {
                        tagNum[lt] = 0;//0表示不在alt构件中，是新系统的组成部分
                    } else {
                        tagNum[lt] = 1;//假如构件已在alt构件中被计算过一次，标记为1，包括tag和tag相连接的构件
                        k++;
                    }
                }
                for (; lt < ca.length; lt++) {
                    tagNum[lt] = 0;
                }
                int[] temNum = new int[ca.length - alt.size() + 1];//创建新构件对应的下标数组
                for (int l = 1, ll = 0; l < temNum.length; ll++) {//tem[0]表示新构件
                    if (tagNum[ll] == 0) {//ll表示遍历一遍原ca的标记数组，l表示为新的temNum数组所对应的原ca中下标数
                        temNum[l] = ll;
                        l++;
                    }
                }
                for (int l = 1; l < temNum.length; l++) {
                    double temp = 0;
                    double temp1 = 0;
                    for (int ll = 0; ll < alt.size(); ll++) {
                        temp += ca[alt.get(ll)][temNum[l]];
                        temp1 += ca[temNum[l]][alt.get(ll)];
                    }
                    temMatrix[0][l] = temp;
                    temMatrix[l][0] = temp1;
                }
                //不相关的复制
                for (int l = 1; l < temNum.length; l++) {//l为原ca的变量，k为新购件集合al的变量，ll为新的矩阵temMatrix的变量
                    for (int ll = 1; ll < temNum.length; ll++) {
                        temMatrix[l][ll] = ca[temNum[l]][temNum[ll]];
                    }
                }
                String[] assemMatrix1 = assemSave(alt, temNum, assemMatrix);
                ca = temMatrix;
                assemMatrix = assemMatrix1;
            }
        }
        //minCom为复杂度最小值
        String result = "";
        if (assemMatrix.length == 1) {
            result = assemMatrix[0];
        } else {
            result = "(";
            int size = assemMatrix.length;
            int cc = 0;
            for (String s : assemMatrix) {
                cc++;
                result = result + s;
                if (cc < size)
                    result += ",";
            }
            result += ")";
        }
        return result;
        /*		System.out.println("mincomplexity="+minCom);		}*/
    }
}
