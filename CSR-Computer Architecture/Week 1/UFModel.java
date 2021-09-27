/*  初始化
	- UF (主要作用是连接分量，并检测连接分量集合的数量)
		- id数组
		- count 方法（检测分量数量）
		- count 变量（存储分量数量）
	统计连接分量
		- find			检测id
		- union 		连接
		- connected		检测是否连接
*/

public class UFModel {
	private int[] id;
	private int count;

	public UF(){						// 初始化分量 id 数组
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public int count(){					// 返回分量数量
		return count;
	}

	public int find(int q){
		return id[q];
	}

	public void union(int p, int q){

	}

	public boolean connected(){			// 检测是否在一个分量中
		return id[q] == id[p]；
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();		// 读取触点数量
		UF uf = new UF();				// 初始化 N 个分量
		while (!StdIn.isEmpty()){
			int p = StdIn.readInt();	
			int q = StdIn.readInt();	// 读取整数对
			if (uf.connected(p,q)) {	// 如果已经连接则忽略
				continue;
			}
			uf.union(p,q);				// 没有连接就连接，归并分量
			StdOut.println(p + " " + q);
		}
		StdOut.println(uf.count() + "components.")
	}
}