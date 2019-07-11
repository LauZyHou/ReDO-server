package com.ecnu.refactoring.core;

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

        while (nset.size() < sum) {

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
            //pmx ���㽻�� �������
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
            //��ʼ����
            for (int i = 0; i < arr1.length; i++) {
                if ((i < point[0]) || (i >= point[1])) {
                    son1[i] = arr1[i];
                    son2[i] = arr2[i];
                } else if ((i >= point[0]) && (i < point[1])) {
                    son1[i] = arr2[i];
                    son2[i] = arr1[i];
                }
            }
            //����������Ϊ0.1
            son1 = mutation(son1, mut_pro);
            son2 = mutation(son2, mut_pro);


            ArrayList<Integer> al2 = new ArrayList<Integer>();

            //����ظ���
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
        //��setת��Ϊal
        ArrayList<int[]> ngro = new ArrayList<int[]>();
        for (int[] is : nset) {
            ngro.add(is);
        }
        return ngro;
    }

    public static ArrayList<Object> select(ArrayList<int[]> group, ArrayList<Double> complexity,
                                           double min_com, int sum, int high) {//high:��ѡ�Ӵ�������sum ��������
        ArrayList<Object> al = new ArrayList<Object>();
        ArrayList<int[]> ngro = new ArrayList<int[]>();
        //���������ø��Ӷȵ�����Ϊ��Ӧ��
        double pro[] = new double[group.size()]; // ��Ⱦɫ��ѡ�����
        double pro_ac[] = new double[group.size()]; // �ۼƸ���
        int[] sche = new int[group.get(0).length];
        ArrayList<Double> fitness = new ArrayList<Double>();
        double value = 0;
        for (int i = 0; i < group.size(); i++) {
            if (complexity.get(i) < min_com) {
                min_com = complexity.get(i);
                sche = group.get(i);//������Сֵ
            }
            double temp = 1.0 / complexity.get(i);//������
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
        //�����µľ���
        double[][] temMatrix = initial_M(ca.length - alt.size() + 1);//��ʼ��Ϊ0,�����С��Ҫ��������temMatrix��ֵ��ca
        //�½�һ���������
        int[] tagNum = new int[ca.length];
        int lt = 0;
        for (int k = 0; (lt < ca.length) && (k < alt.size()); lt++) {
            if (lt != alt.get(k)) {
                tagNum[lt] = 0;//0��ʾ����alt������
            } else {
                tagNum[lt] = 1;//���繹������alt�����б������һ�Σ����Ϊ1������tag��tag�����ӵĹ���
                k++;
            }
        }
        for (; lt < ca.length; lt++) {
            tagNum[lt] = 0;
        }

        //��صĸ���
        temMatrix[0][0] = temp_total;
        int[] temNum = new int[ca.length - alt.size() + 1];//�����¹�����Ӧ���±�����
      //
        //  System.out.println(alt);

        /*System.out.println(temNum.length);*/
        for (int l = 1, ll = 0; l < temNum.length; ll++) {//tem[0]��ʾ�¹���
            if (tagNum[ll] == 0) {//ll��ʾ����һ��ԭca�ı�����飬l��ʾΪ�µ�temNum��������Ӧ��ԭca���±���
                temNum[l] = ll;
                l++;
				/*System.out.println("l:"+l);
				//System.out.println("ll:"+ll);*/
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
        //����صĸ���
        for (int l = 1; l < temNum.length; l++) {//lΪԭca�ı�����kΪ�¹�������al�ı�����llΪ�µľ���temMatrix�ı���
            for (int ll = 1; ll < temNum.length; ll++) {
                temMatrix[l][ll] = ca[temNum[l]][temNum[ll]];
            }
        }
        double complexity = cal_alpha(temMatrix) + cal_data(temMatrix);
        return complexity;
    }

    public static int[] ran_sch(int len) {//�������һ��������Ϊlen

        int arr[] = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Math.random() > 0.5 ? 1 : 0;
        }
        return arr;
    }

    public static ArrayList<int[]> ran_gen(int len, int sum) {

        int total = 0;
        int constr = sum * 1000;//���߽�
        ArrayList<int[]> al = new ArrayList<int[]>();
        for (; (al.size() < sum) && (total < constr); total++) {//����ⱻ�������߳���һ��������������ֹ�����޾�������ȥ
            //���������
            int tem[] = ran_sch(len);
            boolean jud = true;
            //ȥ��
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
    public static int[] cal_cons(double A[][]) {//����ÿ���������ӹ�ϵ��صĹ�����������һ������
        int cons[] = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            cons[i] = cal_each_con(i, A);
        }
        return cons;
    }

    public static int cal_each_con(int c, double A[][]) {//����ÿ�����������Ĺ�����
        int sum = 0;
        //������
        for (int i = 0; i < A.length; i++) {
            if (c != i) {
                sum += A[c][i];
            }
        }
        //����lie
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

    public static double cal_singular(Matrix A) {//��������ֵ����ֵ
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

    public static double[][] initial_M(int len) {//��ʼ������
        double A[][] = new double[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                A[i][j] = 0;
            }
        }
        return A;
    }

    public static double cal_alpha(double data[][]) {
        double temp_total = 0;
        for (int k1 = 0; k1 < data.length; k1++) {
            temp_total += data[k1][k1];
        }
        return temp_total;
    }

    public static double cal_data(double data[][]) {
        double complxity = 0;
        double con[][] = initial_M(data.length);//��ʼ��nά���Ӿ���
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

    public static double sum(double data[][]) {//���� ���� �ĺ�
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
                alt.add(al.get(j));//���Ϊ0�������al�еĸ�Ԫ��
            }
        }
        return alt;
    }

    public static String refactor(double A[][]) {

        double ca[][] = A;
        //һ�α���
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
            }
            ArrayList<Integer> al = getConNum(tag, ca);
            if (al.size() < 17) {
                ArrayList<Integer> alt = new ArrayList();
                int[] group = new int[al.size()];
                for (int j = 0; j < al.size(); j++) {
                    group[j] = 0;
                }
                //���alt
                boolean jud = true;
                String[] assemMatrix1 = assemMatrix;

                double[][] layerTempMatrix = initial_M(ca.length - al.size() + 1);
                while (jud) {
                    //������Ӷȼ���ͱȽ�
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
                    //�����µľ���
                    double[][] temMatrix = initial_M(ca.length - alt.size() + 1);//��ʼ��Ϊ0,�����С��Ҫ��������temMatrix��ֵ��ca
                    //�½�һ���������
                    int[] tagNum = new int[ca.length];
                    int lt = 0;
                    for (int k = 0; (lt < ca.length) && (k < alt.size()); lt++) {
                        if (lt != alt.get(k)) {
                            tagNum[lt] = 0;//0��ʾ����alt�����У�����ϵͳ���ӹ���
                        } else {
                            tagNum[lt] = 1;//1��ʾΪ��alt����alt�������ӹ���
                            k++;
                        }
                    }
                    for (; lt < ca.length; lt++) {
                        tagNum[lt] = 0;
                    }
                    //��صĸ���
                    temMatrix[0][0] = temp_total;
                    int[] temNum = new int[ca.length - alt.size() + 1];//�����¹�����Ӧ���±�����
                    for (int l = 1, ll = 0; l < temNum.length; ll++) {//tem[0]��ʾ�¹���
                        if (tagNum[ll] == 0) {//ll��ʾ����һ��ԭca�ı�����飬l��ʾΪ�µ�temNum��������Ӧ��ԭca���±���
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
                    //����صĸ���
                    for (int l = 1; l < temNum.length; l++) {//lΪԭca�ı�����kΪ�¹�������al�ı�����llΪ�µľ���temMatrix�ı���
                        for (int ll = 1; ll < temNum.length; ll++) {
                            temMatrix[l][ll] = ca[temNum[l]][temNum[ll]];
                        }
                    }
                    //����ڶ���������ʱ����

                    double complexity = cal_alpha(temMatrix) + cal_data(temMatrix);
                    if (complexity < minCom) {
                        minCom = complexity;
                        assemMatrix1 = assemSave(alt, temNum, assemMatrix);
                        layerTempMatrix = new double[temMatrix.length][temMatrix.length];
                        layerTempMatrix = temMatrix;
                    }

                    alt.clear();
                    //���ӷ���Ȼ���������
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
            } else {//������������ʹ���Ŵ�
                //�Ŵ��㷨������300����������100
                //��ʹ�ò��� tag�����Ĺ���������ţ�maxCon�������������������������ã�al�������ӵĹ�����
                int[] bestson = new int[al.size()];
                int sum = 100;//������С
                int maxGen = 300;
                int high = sum / 10;//�����Ӵ�����

                ArrayList<int[]> group = ran_gen(al.size(), sum);
                ArrayList<Double> complexity = new ArrayList<Double>();
                double min_com = minCom;
                for (int numGen = 0; numGen < maxGen; numGen++) {
                    //������Ӧ�ȣ�������comple�У�����arraylistһһ��Ӧ
                    //���㸴�Ӷ�
                    complexity.clear();
                    for (int i = 0; i < group.size(); i++) {
                        double comTem = compute(al, group.get(i), tag, ca);
                        complexity.add(comTem);
                    }
                    //���ݸ��Ӷȼ�����Ӧ��
                    //ѡ��
                    ArrayList<Object> ob = select(group, complexity, min_com, sum, high);
                    //������ֵ
                    group = (ArrayList<int[]>) ob.get(0);
                    double ncom = Double.parseDouble(String.valueOf(ob.get(1)));
                    if (ncom < min_com) {
                        min_com = ncom;
                        //���´������ö��ٴ����仯ֹͣ
                        bestson = (int[]) ob.get(2);
                    }
                    //���棺���㽻��//����:����0.1
                    group = crossover(group, sum, ca);

                }
                //��bestson �� min_com����ת��
                if (min_com == minCom) {
                    break;
                }
                //��װ assem���ͺ���С���Ӷ�
                minCom = min_com;
                ArrayList<Integer> alt = assemAlt(bestson, al, tag);
                double[][] temMatrix = initial_M(ca.length - alt.size() + 1);//��ʼ��Ϊ0,�����С��Ҫ��������temMatrix��ֵ��ca
                //�½�һ���������
                int[] tagNum = new int[ca.length];
                int lt = 0;
                for (int k = 0; (lt < ca.length) && (k < alt.size()); lt++) {
                    if (lt != alt.get(k)) {
                        tagNum[lt] = 0;//0��ʾ����alt�����У�����ϵͳ����ɲ���
                    } else {
                        tagNum[lt] = 1;//���繹������alt�����б������һ�Σ����Ϊ1������tag��tag�����ӵĹ���
                        k++;
                    }
                }
                for (; lt < ca.length; lt++) {
                    tagNum[lt] = 0;
                }
                int[] temNum = new int[ca.length - alt.size() + 1];//�����¹�����Ӧ���±�����
                for (int l = 1, ll = 0; l < temNum.length; ll++) {//tem[0]��ʾ�¹���
                    if (tagNum[ll] == 0) {//ll��ʾ����һ��ԭca�ı�����飬l��ʾΪ�µ�temNum��������Ӧ��ԭca���±���
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
                //����صĸ���
                for (int l = 1; l < temNum.length; l++) {//lΪԭca�ı�����kΪ�¹�������al�ı�����llΪ�µľ���temMatrix�ı���
                    for (int ll = 1; ll < temNum.length; ll++) {
                        temMatrix[l][ll] = ca[temNum[l]][temNum[ll]];
                    }
                }
                String[] assemMatrix1 = assemSave(alt, temNum, assemMatrix);
                ca = temMatrix;
                assemMatrix = assemMatrix1;
            }
        }
        //minComΪ���Ӷ���Сֵ
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
