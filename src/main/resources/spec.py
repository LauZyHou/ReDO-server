"""
给定一个表达构件之间复杂度的矩阵，使用谱聚类将构件聚成若干类
"""

from sklearn.cluster import KMeans
import numpy as np
from typing import List

# 设计结构矩阵，边权表达复杂度
dsm_mtx = []


def calc_similarity_matrix(dsm, k):
    """
    给定设计结构矩阵，计算相似度矩阵
    :param dsm: 设计结构矩阵
    :param k: KNN参数
    :return: 相似度矩阵
    """
    n = dsm.shape[0]  # 构件的数目
    res = np.zeros((n, n))
    for idx, each in enumerate(dsm):
        index_array = np.argsort(each)
        res[idx][index_array[0: k + 1]] = 1
    tmp_W = np.transpose(res)
    W = (tmp_W + res) / 2
    return W


def calc_measure_matrix(W):
    """
    给定相似度矩阵，计算图的度矩阵
    :param W: 相似度矩阵
    :return: 图的度矩阵
    """
    D = np.diag(sum(W))
    return D


def calc_laplacian_matrix(D, W):
    """
    给定图的度矩阵和相似度矩阵，计算拉普拉斯矩阵
    :param W: 相似度矩阵
    :param D: 图的度矩阵
    :return: 拉普拉斯矩阵
    """
    return D - W


def calc_eigenvalues_matrix(L):
    """
    给定拉普拉斯矩阵，计算特征矩阵
    :param L: 拉普拉斯矩阵
    :return: 特征矩阵
    """
    eigval, eigvec = np.linalg.eig(L)
    ix = np.argsort(eigval)[0:cluster_num]
    return eigvec[:, ix]


def gen_table_str(class_list: List) -> str:
    """
    给定聚类后的类别列表，返回头展开形式的广义表，用于给Java后台解析
    :param class_list: 聚类后的类别列表，如[1 0 1 1 1 0]
    :return: 头展开形式的广义表，如((0,2,3,4),1,2)
    """
    # 构件越多的放在越里面，所以这里维护几个集合
    # 然后按集合的元素个数从大到小排序，再去生成结果
    # 类别号 -> 构件下标集合
    set_dict = dict()
    for idx, class_id in enumerate(class_list):
        if class_id not in set_dict:
            set_dict[class_id] = set()
        set_dict[class_id].add(idx)
    # 构件下标集合的列表
    set_list = [st for st in set_dict.values()]
    # 按照集合中元素的数量，从大到小排序
    set_list.sort(key=lambda x: len(x), reverse=True)
    # 生成结果的列表表示
    res = []
    for st in set_list:
        if len(res) > 0:
            res = [res]
        res.extend(st)
    # 转成字符串，方括号变成圆括号，再去除多余空格
    return res.__str__().replace('[', '(').replace(']', ')').replace(' ', '')


if __name__ == '__main__':
    # 读取设计结构矩阵（其中的数值表示构建之间的复杂度）
    with open('src/main/resources/dsm_matrix.txt', 'r') as f:
        for line in f.readlines():
            dsm_mtx.append([float(x) for x in line.strip().split(' ')])
    dsm_mtx = np.array(dsm_mtx)
    # 要聚类成的簇的数量
    cluster_num = 2  # todo 用更科学的方法决定生成的簇的数量
    # KNN投票员的数量
    KNN_k = dsm_mtx.shape[0] // 2
    # 计算相似度矩阵
    W = calc_similarity_matrix(dsm_mtx, KNN_k)
    # 计算图的度矩阵
    D = calc_measure_matrix(W)
    # 给定图的度矩阵和相似度矩阵，计算拉普拉斯矩阵
    L = calc_laplacian_matrix(D, W)
    # 给定拉普拉斯矩阵，计算特征矩阵
    eigvec = calc_eigenvalues_matrix(L)
    clf = KMeans(n_clusters=cluster_num)
    s = clf.fit(eigvec)
    C = s.labels_
    # 计算结果，表示成头元素展开的广义表的形式
    print(gen_table_str(C))
