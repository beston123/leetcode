package jianzhioffer.dp.jingdianwenti;

public class LongestSubstring {
    //最长公共子串要求是连续的
    public int findLongest(String A, int n, String B, int m) {
        int max = 0;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    max = dp[i][j] > max ? dp[i][j] : max;
                }
            }
        return max;
    }
}



/*题目要求公共子串的元素必须相邻， dp矩阵是按照这样的思路想出来的：
首先：
用一个矩阵来记录两个字符串中所有位置的两个字符之间的匹配情况，若是匹配则为1，否则为0。
然后求出对角线最长的1序列，其对应的位置就是最长匹配子串的位置.
下面是字符串21232523311324和字符串312123223445的匹配矩阵，前者为X方向的
，后者为Y方向的。不难找到，红色部分是最长的匹配子串。通过查找位置我们得到最长的匹配子串为：21232
   0 0 0 1 0 0 0 1 1 0 0 1 0 0 0
   0 1 0 0 0 0 0 0 0 1 1 0 0 0 0
   1 0 1 0 1 0 1 0 0 0 0 0 1 0 0
   0 1 0 0 0 0 0 0 0 1 1 0 0 0 0
   1 0 1 0 1 0 1 0 0 0 0 0 1 0 0
   0 0 0 1 0 0 0 1 1 0 0 1 0 0 0
   1 0 1 0 1 0 1 0 0 0 0 0 1 0 0
   1 0 1 0 1 0 1 0 0 0 0 0 1 0 0
   0 0 0 1 0 0 0 1 1 0 0 1 0 0 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 1 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 1 0
   0 0 0 0 0 1 0 0 0 0 0 0 0 0 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
但是在0和1的矩阵中找最长的1对角线序列又要花去一定的时间。
通过改进矩阵的生成方式和设置标记变量，可以省去这部分时间。
优化：
   0 0 0 1 0 0 0 1 1 0 0 1 0 0 0
   0 1 0 0 0 0 0 0 0 2 1 0 0 0 0
   1 0 2 0 1 0 1 0 0 0 0 0 1 0 0
   0 2 0 0 0 0 0 0 0 1 1 0 0 0 0
   1 0 3 0 1 0 1 0 0 0 0 0 1 0 0
   0 0 0 4 0 0 0 2 1 0 0 1 0 0 0
   1 0 1 0 5 0 1 0 0 0 0 0 2 0 0
   1 0 1 0 1 0 1 0 0 0 0 0 1 0 0
   0 0 0 2 0 0 0 2 1 0 0 1 0 0 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 1 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 1 0
   0 0 0 0 0 1 0 0 0 0 0 0 0 0 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

          当字符匹配的时候，我们并不是简单的给相应元素赋上1，而是赋上其左上角元素的值加一。
          我们用两个标记变量来标记矩阵中值最大的元素的位置，在矩阵生成的过程中来判断当前生成的元素的值是不是最大的，
          据此来改变标记变量的值，那么到矩阵完成的时候，最长匹配子串的位置和长度就已经出来了。*/