package mathLib;/** * @author kamakiri01 * @version  0.81 26 November 2012 * * This library manages (m,n)Matrix by double[m][n]. * An element(m,n) is NOT matching matrix[m][n], its point of matrix[m-1][n-1]. *//** * 行列のクラス。<br> * 行数、列数、実体のdouble二次元配列は全てfinal属性。 *  * */public class Matrix implements Cloneable{	/** 	 * 列数	 */	protected final int m; //number of row	/**	 * 行数	 */	protected final int n; //number of column	/**	 * 行列要素の実体	 */	protected final double [][] value; //entity of matrix elements	/**	 * (m,m)型の空の正方行列を作るコンストラクタ	 * 	 */	public Matrix(int m){		this.m = m;		this.n = m;		value = new double[m][m];	}		/**	 * (m,n)型の空行列を作るコンストラクタ	 *	 * @param m	 * m行の行列を生成する	 * @param n	 *　n列の行列を生成する	 */	public Matrix(int m, int n){		this.m = m;		this.n = n;		value = new double[m][n];		for(int i = 0;i<this.m;i++){			for(int j  =0;j<this.n;j++){				this.value[i][j] = 0;			}		}	}		/**	 * 値を指定して行列を作るコンストラクタ<br>	 * 	 * Matrix mydata = new Matrix({<br>	 * 		{1,0,0},<br>	 * 		{0,1,0},<br>	 * 		{0,0,1}});<br>	 *	 * @param source	 * 	 * 行列の実体となる2次元配列	 */	public Matrix(double[][] source){		m = source.length;		n = source[0].length;		this.value = new double[m][n];		for(int i = 0;i<this.m;i++){			for(int j  =0;j<this.n;j++){				this.value[i][j] = source[i][j];			}//			this.value[i] = Arrays.copyOf(source[i], source[i].length);		}	}			/**	 * 値を指定して行列を作るコンストラクタ(一次元配列)<br>	 * 	 * Matrix mydata = new Matrix(3,3,{<br>	 * 		1,0,0,<br>	 * 		0,1,0,<br>	 * 		0,0,1);<br>	 *	 * @param m	 * m行の行列を生成する	 * @param n	 *　n列の行列を生成する	 * @param source	 * 行列の実体となる一次元配列	 * @exception java.lang.RuntimeException.RuntimeException 行数、列数の積が配列長と一致しない場合に発生	 */	public Matrix(int m, int n, double[] source){		if(m*n != source.length){			throw new RuntimeException("行数、列数と配列長が一致しません");		}		this.m = m;		this.n = n;		this.value = new double[m][n];		for(int i = 0;i<this.m;i++){			for(int j =0;j<this.n;j++){				this.value[i][j] = source[j + n*i];			}		}	}			/**	 * 行列を与えてコピー行列を作るコンストラクタ(ディープコピー)	 * 	 * @param source	 * コピー元行列のvalue	 * 	 */	public Matrix(Matrix source){		this(source.value);	}		//	/**	 * (m,m)の単位行列を作るコンストラクタ	 * 	 * @param m	 * 単位行列の辺の要素数	 * @return 	 * m行m列の単位行列	 */	public static Matrix identityMatrix(int m){		Matrix result = new Matrix(m);		for(int i = 0;i<m;i++){			for(int j =0;j<m;j++){				if(i == j){					result.value[i][j] = 1;				}else{					result.value[i][j] = 0;				}			}		}		return result;	}		/**	 * 行列の実体の二次元配列を返す。	 * @return	 * 行列の二次元配列	 */	public double[][] getValue(){		return this.value;	}	/**	 * 行列の列数を返す。	 * @return	 * 行列の列数	 */	public int getCols(){		return this.n;	}	/**	 * 行列の行数を返す。	 * @return	 * 行列の行数	 */	public int getRows(){		return this.m;	}    /**     * 行列をベクトルに変換する     * @param source     * 変換元行列（元の行列は変更されない）     * @return     * 変換されたベクトル	 * @exception java.lang.RuntimeException.RuntimeException 変換元が行、または列ベクトルの行列表現でない場合に発生     */    public Vector toVector(Matrix source) {		if(source.m != 1 && source.n != 1){			throw new RuntimeException("変換元は行、または列ベクトルである必要があります");		}    	double[][] value = new double[source.m][source.n];    	for (int i = 0; i < source.m; i++) {        	for (int j = 0; j < source.n; j++) {    			value[i][j] = source.e(i, j);        	}    	}    	return new Vector(value);    }		/**	 * 行列の各要素をスカラー倍した新しい行列を返す。	 * 元の行列は変更されない	 * 	 * @param scalar	 * 倍率	 * @return	 * scalar倍した行列。	 * @see Vector#scalarMultiple(double)	 */	public Matrix scalarMultiple(double scalar){		int m = this.m;		int n = this.n;		Matrix result = new Matrix(m,n);//this.mirror();		for(int i = 0;i<m;i++){			for(int j =0;j<n;j++){				result.value[i][j] = this.value[i][j] * scalar;			}		}		return result;	}		/**	 * 次元の等しい行列2つの要素をそれぞれ足し合わせた行列を返す。	 * 元の行列は変更されない	 * @param source	 * 加算する行列	 * @return	 * 加算された行列	 * @exception java.lang.RuntimeException.RuntimeException 適切な行列の組み合わせでない場合に発生	 * @see Vector#add(Vector)	 */	public Matrix add(Matrix source){		if(this.m != source.m || this.n != source.n){			throw new RuntimeException("行列の次元が一致しません");		}		int m = this.m;		int n = this.n;		Matrix result = new Matrix(m,n);//this.mirror();		for(int i = 0;i<m;i++){			for(int j =0;j<n;j++){						result.value[i][j] = this.value[i][j] + source.value[i][j];			}		}		return result;	}	/**	 * 次元の等しい行列2つの要素をそれぞれ引いた行列を返す。	 * 元の行列は変更されない	 * @param source	 * 減算する右項の行列	 * @return	 * 減算された行列	 * @exception java.lang.RuntimeException.RuntimeException 適切な行列の組み合わせでない場合に発生	 * @see Vector#sub(Vector)	 */	public Matrix sub(Matrix source){		if(this.m != source.m || this.n != source.n){			throw new RuntimeException("行列の次元が一致しません");		}		int m = this.m;		int n = this.n;		Matrix result = new Matrix(m,n);//this.mirror();		for(int i = 0;i<m;i++){			for(int j =0;j<n;j++){						result.value[i][j] = this.value[i][j] - source.value[i][j];			}		}		return result;	}		/**	 * 転置行列を返す。元の行列は変更されない	 * 	 * @return	 * 転置行列	 */	public Matrix transpose(){		Matrix result = new Matrix(this.n, this.m);		for(int i = 0;i<n;i++){			for(int j =0;j<m;j++){				result.value[i][j] = this.value[j][i];			}		}				return result;	}		/**	 * elementMatrixの短縮表現	 * @param m	 * 取り出す要素の行番号	 * @param n	 * 取り出す要素の列番号	 * @return	 * m行n列目にある要素	 */	public double e(int m, int n){		return this.elementMatrix(m, n);	}		/**	 * 行列の(m,n)にある要素を返す。元の行列は変更されない	 * @param m	 * 取り出す要素の行番号	 * @param n	 * 取り出す要素の列番号	 * @return	 * m行n列目にある要素	 */	public double elementMatrix(int m, int n){		double result;		result = this.value[m][n];		return result;	}		/**	 * コピーした行列を返す。元の行列は変更されない	 * @return	 * 行列のコピー	 */	public Matrix mirror(){		Matrix result = new Matrix(this.value);//		for(int i = 0;i<result.m;i++){//			for(int j =0;j<result.n;j++){//				result.value[i][j] = this.value[i][j];//			}//		}				return result;	}		/**	 * 行列の要素がすべて等しいか調べて返す。元の行列は変更されない	 * @param source	 * 調べる対象の行列	 * @return	 * 比較結果の真偽値	 */	public Boolean eq(Matrix source){		if(this.m != source.m || this.n != source.n){			throw new RuntimeException("行列の次元が一致しません");		}		for(int i = 0;i<this.m;i++){			for(int j =0;j<this.n;j++){				if(this.value[i][j] != source.value[i][j]){					return false;				}			}		}		return true;//		if(this.value.equals(source.value)){//			return true;//		}else{//			return false;//		}	}		/**	 * 行列を単位行列化して返す。元の行列は変更されない	 * @return	 * 単位化された行列	 */	public Matrix toIdentity(){		Matrix result = new Matrix(this.value);		double det = this.getDet();		result = result.scalarMultiple(det);		return result;	}		/**	 * 行列を正規化して返す	 * toIdentityメソッドの別名	 * @return	 * 正規化された行列	 */	public Matrix normalize(){		return this.toIdentity();	}		/**	 * MatrixをSystem.out.printlnする	 */	public void printMatrix(){		int m = this.m;		int n = this.n;		for(int i = 0;i<m;i++){			for(int j =0;j<n;j++){						if(j == 0){					System.out.print("[");				}				System.out.print(this.value[i][j]);				if(j+1 == n){					System.out.println("]");				}else{					System.out.print(", ");				}			}		}	}		/**	 * 任意の1行を取り除いた行列を返す。元の行列は変更されない	 * @param away	 * 取り除く行番号 0〜m-1	 * @return	 * 一行減らされた(m-1, n)行列	 * @exception java.lang.RuntimeException.RuntimeException 適切な行列と引数でない場合に発生	 */	public Matrix omitRow(int away){		Matrix source = new Matrix(this);//this.mirror();		if(source.m <2){			source.printMatrix();			throw new RuntimeException("(m,1)の行列から1行減らすことはできません");		}else if(source.m < away){			throw new RuntimeException("取り除く行は与えられた行列にありません");		}		Matrix tmp = new Matrix(source.m-1, source.n);		int dif = 0;		for(int i = 0;i<source.m-1;i++){			if(i == away){				dif++;			};			tmp.value[i] = source.value[i+dif];		}		return tmp;	}		/**	 * 任意の1列を取り除いた行列を返す。元の行列は変更されない	 * @param away	 * 取り除く行番号 0〜n-1	 * @return	 * 一列減らされた(m, n-1)行列	 * @exception java.lang.RuntimeException.RuntimeException 適切な行列と引数でない場合に発生	 */	public Matrix omitCol(int away){		Matrix source = new Matrix(this);//this.mirror();		if(source.n <2){//			Matrix.printMatrix(source);			throw new RuntimeException("(1,n)の行列から1列減らすことはできません");		}else if(source.n < away || away < 0){			throw new RuntimeException("取り除く列は与えられた行列にありません");		}		Matrix tmp = new Matrix(source.m, source.n-1);		for(int i = 0;i<source.m;i++){			int dif = 0;			for(int j =0;j<source.n-1;j++){				if(j == away){					dif = 1;				}				tmp.value[i][j] = source.value[i][j+dif];			}		}		return tmp;	}	/**	 * 行列の(j,k)余因子を返す。元の行列は変更されない	 * @param coJ	 * 除かれる行の番号	 * @param coK	 * 除かれる列の番号	 * @return	 * (j, k)余因子	 * @exception java.lang.RuntimeException.RuntimeException 適切な行列でない場合に発生	 */	public double matrixCofactor(int coJ, int coK){		Matrix source = new Matrix(this);//this.mirror();		int m = source.m;		int n = source.n;		if(m != n || n == 0){			throw new RuntimeException("余因子を生成できない行列です");		}		double result = 0;		//old layout//		Matrix tmp = omitColumnMatrix(//						omitRowMatrix(source, coJ)//							, coK);		Matrix tmp = source.omitCol(coK).omitRow(coJ);		result = tmp.getDet() * Math.pow(-1, coK+1+coJ+1);				return result;	}		//掃き出し法があればoverrideすると高速化できるかもしれません	/**	 * 行列式の値detAを返す。元の行列は変更されない	 * @return	 * 行列式の値	 * @exception java.lang.RuntimeException.RuntimeException 適切な行列でない場合に発生	 */	public double getDet(){		Matrix source = new Matrix(this);//this.mirror();		int m = source.m;		int n = source.n;		if(m != n || n == 0){			throw new RuntimeException("行列式を生成できない行列です");		}		double result = 0;		if(n == 1){			//1x1行列の場合は値をそのまま返す			result = source.value[0][0];		}		if(n == 2){			//(2,2)型行列のdetは公式を用いる			result = source.value[0][0]*source.value[1][1] - source.value[0][1]*source.value[1][0];		}else{			//(3,3)以上であれば再帰定義する。			for(int j=0;j<n;j++){				result += source.value[0][j] * source.matrixCofactor(0,j);			}		}		return result;	}		//掃き出し法があればoverrideすると高速化できるかもしれません	/**	 * 逆行列を返す。元の行列は変更されない 	 * @return	 * Matrix型の逆行列	 * @exception java.lang.RuntimeException.RuntimeException 適切な行列でない場合に発生	 */	public Matrix inverse(){		Matrix source = new Matrix(this);//this.mirror();		if(source.m != source.n || source.n < 2){			throw new RuntimeException("逆行列を生成できない行列です");		}		Matrix result = new Matrix(source.m, source.n);		if(source.n == 2){			//(2,2)の逆行列			result.value[0][0] = source.value[1][1];			result.value[1][1] = source.value[0][0];			result.value[0][1] = source.value[0][1] * (-1);			result.value[1][0] = source.value[1][0] * (-1);		}else{			//一般形の逆行列			for(int i = 0;i<source.m;i++){				for(int j =0;j<source.n;j++){					result.value[i][j] = source.matrixCofactor(j, i);//source.value[j][i];				}			}					}		if(source.getDet() == 0){			throw new RuntimeException("正則行列ではありません");		}		result = result.scalarMultiple( 1/source.getDet());		return result;	}		/**	 * 自身に引数の行列を右からかけた積の結果を返す。元の行列は変更されない	 * @param source	 * 右にかける行列	 * @return	 * Matrix型行列	 * @exception java.lang.RuntimeException.RuntimeException 適切な行列の組み合わせでない場合に発生	 */	public Matrix multiply(Matrix source){		int m1 = this.m;		int n1 = this.n;		int m2 = source.m;		int n2 = source.n;		if(n1 != m2){			throw new RuntimeException("積を得られない行列の組み合わせです");		}		Matrix result = new Matrix(m1, n2);		for(int i = 0;i<result.m;i++){			for(int j =0;j<result.n;j++){								for(int k = 0;k<n;k++){					result.value[i][j] += this.value[i][k]*source.value[k][j];				}			}		}		return result;	}				/**	 * 行列の要素をString型に変換して返す	 */	public String toString(){		return this.value.toString();	}}