package mathLib;
/**
 * @author kamakiri01
 * @version  0.81 26 November 2012
 *
 */

/**
 * ベジェ曲線クラス。<br>
 * 制御点の座標配列と描画間隔数によって、描画される曲線をなす座標の配列を返す。<br>
 * 
 *
 */
public class Bezier {

	/**
	 * ベジェ曲線をなす座標の配列を返す。
	 * @param controlPoints
	 * 制御点のベクトル配列。
	 * @param fragments
	 * 曲線を描画する点数
	 * @return
	 * 曲線をなす座標のベクトル配列（行ベクトルで出力）。
	 */
	public static Vector[] bezierCurve(Vector[] controlPoints, int fragments){
		double tFrags = (double)1/fragments; //ｔの間隔を描画数の逆数から求める
		int cNums = controlPoints.length; //制御点の数
		int tmpCounter = 0;//result配列代入番号
		int dim = controlPoints[0].n;//曲線が存在する座標系の次元
//		System.out.println("tFrags:"+tFrags);

		Vector[] result = new Vector[fragments];

		for(double i=0;i<1-tFrags;i+=tFrags){ //tは０～１までtFrags刻みで動かす
			//各t値毎のベジェ座標の配列を用意
			double[] bezierValue = new double[dim];
			for(int d=0;d<dim;d++){
				bezierValue[d] = 0;
			}

			//各制御点から曲線上の座標を得て加算する
			for(int j=0;j<cNums;j++){
				//座標値の取得を次元の数だけ繰り返す
				for(int d=0;d<dim;d++){

					bezierValue[d] += 
							  Math.pow(i, j)
							* Math.pow((1-i), (cNums-j-1))
							* controlPoints[j].e(0, d)
							* pascalTriangle(cNums, j);
				}
			}
			
			Vector tmpVec = new Vector(bezierValue);
			result[tmpCounter] = tmpVec;
			tmpCounter++;
		}
		return result;
		
		
		
	}
	
	/**
	 * パスカルの三角形係数を得る
	 * @param n
	 * 何項の底辺まで作るか（上からｎ段目まで）
	 * @param k
	 * 左からｋ番目（ゼロからk-1インデックス）
	 * @return
	 * 係数値
	 */
	//この方法だと14項くらいで破綻するので使わない
//	public static int pascalTriangle(int n, int k){
//		int result = 0;
//		result = (int)factorial(n)
//					/factorial(n-k)
//					/factorial(k);
//		return result;
//	}
	public static int pascalTriangle(int n, int k){
	      int[][] val = new int[n][n];
	        
	        for(int i = 0; i <n; i++) {
	            for(int j = 0; j <= i; j++) {
	                if(j == 0)
	                    val[i][j] = 1;
	                else
	                    val[i][j] = val[i-1][j-1] + val[i-1][j];
	            }
	        }
	        return val[n-1][k];
	};
	
	//階乗する
	private static int factorial(int n){
        int fact=1;
        if(n==0){
        	return fact;
        }else{
            for(int i=n; i>0; i--){
                fact*=i;
            }
            return fact;
       }
    }
}
