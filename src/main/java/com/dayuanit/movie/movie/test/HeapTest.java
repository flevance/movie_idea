package com.dayuanit.movie.movie.test;

public class HeapTest {

    public static int[] nums = {2,9,5,3,1,6,7};


    /**
     * 设置堆数组,长度比原数组大1,因为0索引不使用
     */
    public static int[] heapArray = new int[nums.length + 1];
    /**
     * 堆数组的长度
     */
    public static int size;

    public static void main(String[] args) {
        for (int num : nums){
            add(num);

        }
        for (int i= 0;i<nums.length;i++){
            System.out.print(remove());
        }

    }

    /**
     * 将数字进行交换
     * @param index1 第一个值
     * @param index2 第二个值
     */
    public static void swap(int index1,int index2){

        int temp = heapArray[index1];
        heapArray[index1] = heapArray[index2];
        heapArray[index2] = temp;
    }

    /**
     * 将数组堆化
     * @param element 依次传入数组的参数
     */
    public static void add(int element){
        //为堆数组添加元素
        heapArray[++size] = element;
        //添加元素的父节点的索引
        int currentParent = size / 2;
        //添加元素的索引
        int currentNote = size;

        while (currentParent > 0){
            //判定,如果添加元素比数组元素要小,那么就会进入循环,将元素与父节点交换位置
            if (heapArray[currentNote] < heapArray[currentParent]){
                //两个节点直接交换位置
                swap(currentNote,currentParent);

                currentNote = currentParent;
                //父节点替代了子节点的位置
                currentParent = currentParent /2;
                continue;
            }
            break;
        }
    }

    /**
     * 下沉操作,通过循环这个方法完成排序
     * @return 返回的是当次循环的最小值
     */
    public static int remove(){
        //在初始化堆之后就会产生一个堆顶,这个值是最大值或最小值
        int targetNum = heapArray[1];
        //将堆顶和最后一个数交换位置
        swap(1,size--);
        //定义从第一个开始处理.因为第0个不参与排序
        int currentNode = 1;

        while (true){
            //定义堆顶下的两个分支
            int left = currentNode * 2;
            int right = currentNode * 2 + 1;
            //首先先假定添加这个数是最小的
            int min = currentNode;
            //进行判断,如果这个数比它子叶左边的大,那么最小的就变成左子叶
            if (left <= size && heapArray[min] > heapArray[left]){
                min = left;
            }
            //再进行判断,如果这个数比它子叶的右边大,那么最小的索引就变成了右子叶
            if (right <= size && heapArray[min] > heapArray[right]){
                min = right;
            }
            //判断假定的那个子叶是否还是原来的索引,如果不是,那么就交换位置
            if (min != currentNode){
                swap(min,currentNode);
                currentNode = min;
                continue;
            }
            break;
        }
        //返回原先的顶堆
        return targetNum;
    }
}
