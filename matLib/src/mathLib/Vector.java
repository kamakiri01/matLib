package mathLib;
/**
 * @author kamakiri01
 * @version  0.81 26 November 2012
 *
 * This library manage (m,n)Matrix by double[m][n].
 * An element(m,n) is NOT matching matrix[m][n], its point of matrix[m-1][n-1].
 */

/**
 * �x�N�g���̃N���X�B�s�܂��͗񐔂�1�́A�����t���s��N���X�̕ʖ��B<br>
 * �s���A�񐔁A���̂�double�񎟌��z��͑S��final�����B
 * 
 * @see mathLib.Matrix
 *
 */
public class Vector extends Matrix{
	
	//
	/**
	 * �l���w�肵�ăx�N�g�������R���X�g���N�^<br>
	 * 
	 * ��x�N�g���̐���<br>
	 * Matrix myCol = new Vector({<br>
	 * 		{1},<br>
	 * 		{0},<br>
	 * 		{0}});<br>
	 * 
	 * �s�x�N�g���̐���<br>
	 * Matrix myRow = new Vector({<br>
	 * 		{1,0,0}});<br>
	 * @param source
	 * �v�f����������x�N�g��
	 * @exception java.lang.RuntimeException.RuntimeException �s�����邢�͗񐔂�1�łȂ��ꍇ�ɔ���
	 */
	public Vector(double[][] source){
		super(source);
		if(source.length != 1 && source[0].length != 1){
			throw new RuntimeException("�x�N�g���𐶐��ł��Ȃ��z��ł�");
		}
	}
	
	/**
	 * �l���w�肵�ė�x�N�g�������R���X�g���N�^(�ꎟ���z��)<br>
	 * 
	 * 
	 * Matrix mydata = new Vector(3,{<br>
	 * 		3,<br>
	 * 		2,<br>
	 * 		1});<br>
	 *
	 * @param source
	 * �x�N�g���̎��̂ƂȂ�ꎟ���z��
	 */
	public Vector(double[] source){
		super(1, source.length, source);
		this.toCol();
	}
	
	//
	/**
	 * (m,n)�^�̋�x�N�g�������R���X�g���N�^�BVector�^�̐������̂��߃N���X�O�ł͎g�p����Ȃ�
	 * @param m
	 * m�s�̃x�N�g���𐶐�����
	 * @param n
	 *�@n��̃x�N�g���𐶐�����
	 */
	private Vector(int m, int n){
		super(m,n);
	}
	
	//
	/**
	 * �v�f��(m, 1)�A�v�f�͂��ׂ�0�̗�x�N�g�������R���X�g���N�^
	 * @param m
	 * �x�N�g���̗v�f��
	 */
	public Vector(int m){
		super(m, 1);
	}
	
	/**
	 * �x�N�g�����s�x�N�g���ɂ��ĕԂ��B���̃x�N�g���͕ύX����Ȃ�
	 * @return
	 * ��s�x�N�g��
	 */
	public Vector toRow(){
		Matrix result = new Vector(this.value);
		if(result.m != 1){
			result = result.transpose();
		}
		return new Vector(result.value);
	}

	/**
	 * �x�N�g�����x�N�g���ɂ��ĕԂ��B���̃x�N�g���͕ύX����Ȃ�
	 * @return
	 * ���x�N�g��
	 */
	public Vector toCol(){
		Matrix result = new Vector(this.value);
		if(result.n != 1){
			result = result.transpose();
		}
		return new Vector(result.value);
	}
	
	/**
	 * �x�N�g���̎��ȓ��ς̒l��Ԃ��B�x�N�g���̑傫���ɓ�����
	 * @return
	 * �x�N�g���̑傫��
	 */
	public double size(){
		double result = 0;
		result = this.dotProduct(this);
		result = Math.sqrt(result);
		return result;
	}
	
	//�x�N�g�����X�J���[�{���ĕԂ�
	//�c�x�N�g���A���x�N�g�����͎w�肵�Ȃ�
	/* (�� Javadoc)
	 * @see Matrix#scalarMultiple(double)
	 */
	/**
	 * �x�N�g���̊e�v�f���X�J���[�{�����V�����x�N�g����Ԃ��B
	 * ���̃x�N�g���͕ύX����Ȃ�
	 * 
	 * @param scalar
	 * �{��
	 * @return
	 * scalar�{�����x�N�g���B
	 * @see Matrix#scalarMultiple(double)
	 */
	public Vector scalarMultiple(double scalar){
		Vector result = new Vector(this.value);
		for(int i = 0;i<result.m;i++){
			for(int j =0;j<result.n;j++){
				result.value[i][j] = result.value[i][j] * scalar;
			}
		}
		return result;
	}
	

	/* (�� Javadoc)
	 * �����̓������x�N�g��2�̗v�f�����ꂼ�ꑫ�����킹���x�N�g����Ԃ��B
	 * ���̃x�N�g���͕ύX����Ȃ�
//	 * �c���̎������قȂ�ꍇ�͍����ɍ��킹��
	 * @see Matrix#add(Matrix)
	 */
	/**
	 * �����̓������x�N�g��2�̗v�f�����ꂼ�ꑫ�����킹���x�N�g����Ԃ��B
	 * ���̃x�N�g���͕ύX����Ȃ�
	 * @param source
	 * ���Z����x�N�g��
	 * @return
	 * ���Z���ꂽ�x�N�g��
	 * @exception java.lang.RuntimeException.RuntimeException �K�؂ȃx�N�g���̑g�ݍ��킹�łȂ��ꍇ�ɔ���
	 * @see Matrix#add(Matrix)
	 */
	public Vector add(Vector source){
//		
//		if(this.m == 1){
//			//�s�x�N�g���ɍ��킹��
//		}else if(this.n == 1){
//			//��x�N�g���ɍ��킹��
//		}
		if(this.m != source.m || this.n != source.n){
			throw new RuntimeException("�x�N�g���̎�������v���܂���");
		}

		int m = this.m;
		int n = this.n;
		Vector result = new Vector(m,n);//this.mirror();
		for(int i = 0;i<m;i++){
			for(int j =0;j<n;j++){		
				result.value[i][j] = this.value[i][j] + source.value[i][j];
			}
		}
		return result;
	}
	/* (�� Javadoc)
	 * �����̓������x�N�g��2�̗v�f�����ꂼ��������x�N�g����Ԃ��B
	 * ���̃x�N�g���͕ύX����Ȃ�
//	 * �c���̎������قȂ�ꍇ�͍����ɍ��킹��
	 * @see Matrix#sub(Matrix)
	 */
	/**
	 * �����̓������x�N�g��2�̗v�f�����ꂼ��������x�N�g����Ԃ��B
	 * ���̃x�N�g���͕ύX����Ȃ�
	 * @param source
	 * ���Z����E���̃x�N�g��
	 * @return
	 * ���Z���ꂽ�x�N�g��
	 * @exception java.lang.RuntimeException.RuntimeException �K�؂ȃx�N�g���̑g�ݍ��킹�łȂ��ꍇ�ɔ���
	 * @see Matrix#sub(Matrix)
	 */
	public Vector sub(Vector source){
//		
//		if(this.m == 1){
//			//�s�x�N�g���ɍ��킹��
//		}else if(this.n == 1){
//			//��x�N�g���ɍ��킹��
//		}
		if(this.m != source.m || this.n != source.n){
			throw new RuntimeException("�x�N�g���̎�������v���܂���");
		}

		int m = this.m;
		int n = this.n;
		Vector result = new Vector(m,n);//this.mirror();
		for(int i = 0;i<m;i++){
			for(int j =0;j<n;j++){		
				result.value[i][j] = this.value[i][j] - source.value[i][j];
			}
		}
		return result;
	}	
	/* (�� Javadoc)
	 * �x�N�g����P�ʍs�񉻂��ĕԂ�
	 * @see Matrix#toIdentity()
	 */
	/**
	 * �x�N�g����P�ʍs�񉻂��ĕԂ��B���̃x�N�g���͕ύX����Ȃ�
	 * @return
	 * �P�ʉ����ꂽ�s��
	 */
	public Vector toIdentity(){
		Vector result = new Vector(this.value);
		double scale = Math.sqrt(this.dotProduct(this));
		if(scale == 0){
			return this;
		}
		result = result.scalarMultiple(1/scale);
		return result;
	}
	
	public Vector normalize(){
		return this.toIdentity();
	}

	/**
	 * ���g�ƈ����x�N�g���̓��ς̌��ʂ�Ԃ��B���̃x�N�g���͕ύX����Ȃ�
	 * 
	 * @param source
	 * �C�ӂ̃x�N�g���B���g�ƒ�������v���Ă��Ȃ��ꍇ�G���[��Ԃ��B
	 * 
	 * @return
	 * ���ϒl
	 * @exception java.lang.RuntimeException.RuntimeException �K�؂ȃx�N�g���̑g�ݍ��킹�łȂ��ꍇ�ɔ���
	 */
	public double dotProduct(Vector source){
		Vector tmp1 = this.toCol();
		Vector tmp2 = source.toCol();
		
		if(tmp1.m != tmp2.m){
			throw new RuntimeException("�x�N�g��������v���Ă��܂���");
		}
		double result = 0;
		for(int i = 0;i<tmp1.m;i++){
			result += tmp1.value[i][0] * tmp2.value[i][0];
		}
		return result;
	}
	/**
	 * ���g�ƈ����x�N�g���̓��ς𐳋K���������ʂ�Ԃ��B
	 * ���̃x�N�g���͕ύX����Ȃ�
	 * 
	 * @param source
	 * �C�ӂ̃x�N�g���B���g�ƒ�������v���Ă��Ȃ��ꍇ�G���[��Ԃ��B
	 * 
	 * @return
	 * ���K�����ꂽ���ϒl
	 * @exception java.lang.RuntimeException.RuntimeException �K�؂ȃx�N�g���̑g�ݍ��킹�łȂ��ꍇ�ɔ���
	 */
	public double dotProductNormalize(Vector source){
//		Vector tmp1 = this.toCol();
//		Vector tmp2 = source.toCol();
//		
//		if(tmp1.m != tmp2.m){
//			throw new RuntimeException("�x�N�g��������v���Ă��܂���");
//		}
//		double result = 0;
//		for(int i = 0;i<tmp1.m;i++){
//			result += tmp1.value[i][0] * tmp2.value[i][0];
//		}
//		result = result/this.size();
//		result = result/source.size();
//		System.out.println("getDotSourceP");
//		Vector.printMatrix(this);
//		Vector.printMatrix(source);
		Vector tmp1 = this.toCol().normalize();
		Vector tmp2 = source.toCol().normalize();
		
//		System.out.println("getDotSource");
//		Vector.printMatrix(tmp1);
//		Vector.printMatrix(tmp2);
		
		if(tmp1.m != tmp2.m){
			throw new RuntimeException("�x�N�g��������v���Ă��܂���");
		}
//		System.out.println("dPN-cl: "+tmp1.m +":"+tmp2.m);
		double result = 0;
		for(int i = 0;i<tmp1.m;i++){
			result += tmp1.value[i][0] * tmp2.value[i][0];
//			System.out.println("dPN-i: "+tmp1.value[i][0] + ":"+tmp2.value[i][0]);
//			System.out.println("dPN: "+result);
		}
//		System.out.println("dPN: "+result);
		return result;
	}
	
	/**
	 * ���g�ƈ����x�N�g���̊O�ς̌��ʂ�Ԃ��B
	 * �����ꂩ��3�����ȊO�̃x�N�g���̏ꍇ�G���[��Ԃ��B���̃x�N�g���͕ύX����Ȃ�
	 * @param source
	 * �C�ӂ̃x�N�g���B���g�ƒ�������v���Ă��Ȃ��ꍇ�G���[��Ԃ��B
	 * 
	 * @return
	 * �O�σx�N�g��
	 * @exception java.lang.RuntimeException.RuntimeException �K�؂ȃx�N�g���̑g�ݍ��킹�łȂ��ꍇ�ɔ���
	 * @exception java.lang.RuntimeException.RuntimeException 3�����x�N�g���ȊO�̏ꍇ�ɔ���
	 */
	public Vector crossProduct(Vector source){
		Vector tmp1 = this.toCol();
		Vector tmp2 = source.toCol();
		
		if(tmp1.m != tmp2.m){
			throw new RuntimeException("�x�N�g��������v���Ă��܂���");
		}else if(tmp1.m != 3){
			throw new RuntimeException("3�����ȊO�̃x�N�g���ɂ͓K�p�ł��܂���");
		}
		
	Vector result = new Vector(tmp1.m);
	result.value[0][0] = tmp1.value[1][0]*tmp2.value[2][0] - tmp1.value[2][0]*tmp2.value[1][0];  
	result.value[1][0] = tmp1.value[2][0]*tmp2.value[0][0] - tmp1.value[0][0]*tmp2.value[2][0];  
	result.value[2][0] = tmp1.value[0][0]*tmp2.value[1][0] - tmp1.value[1][0]*tmp2.value[0][0];  
	return result;
	}
	/**
	 * �x�N�g���̗v�f��String�^�ɕϊ����ĕԂ�
	 */
	public String toString(){
		return this.value.toString();
	}
}