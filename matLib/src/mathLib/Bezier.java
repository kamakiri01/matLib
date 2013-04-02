package mathLib;
/**
 * @author kamakiri01
 * @version  0.81 26 November 2012
 *
 */

/**
 * �x�W�F�Ȑ��N���X�B<br>
 * ����_�̍��W�z��ƕ`��Ԋu���ɂ���āA�`�悳���Ȑ����Ȃ����W�̔z���Ԃ��B<br>
 * 
 *
 */
public class Bezier {

	/**
	 * �x�W�F�Ȑ����Ȃ����W�̔z���Ԃ��B
	 * @param controlPoints
	 * ����_�̃x�N�g���z��B
	 * @param fragments
	 * �Ȑ���`�悷��_��
	 * @return
	 * �Ȑ����Ȃ����W�̃x�N�g���z��i�s�x�N�g���ŏo�́j�B
	 */
	public static Vector[] bezierCurve(Vector[] controlPoints, int fragments){
		double tFrags = (double)1/fragments; //���̊Ԋu��`�搔�̋t�����狁�߂�
		int cNums = controlPoints.length; //����_�̐�
		int tmpCounter = 0;//result�z�����ԍ�
		int dim = controlPoints[0].n;//�Ȑ������݂�����W�n�̎���
//		System.out.println("tFrags:"+tFrags);

		Vector[] result = new Vector[fragments];

		for(double i=0;i<1-tFrags;i+=tFrags){ //t�͂O�`�P�܂�tFrags���݂œ�����
			//�et�l���̃x�W�F���W�̔z���p��
			double[] bezierValue = new double[dim];
			for(int d=0;d<dim;d++){
				bezierValue[d] = 0;
			}

			//�e����_����Ȑ���̍��W�𓾂ĉ��Z����
			for(int j=0;j<cNums;j++){
				//���W�l�̎擾�������̐������J��Ԃ�
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
	 * �p�X�J���̎O�p�`�W���𓾂�
	 * @param n
	 * �����̒�ӂ܂ō�邩�i�ォ�炎�i�ڂ܂Łj
	 * @param k
	 * �����炋�Ԗځi�[������k-1�C���f�b�N�X�j
	 * @return
	 * �W���l
	 */
	//���̕��@����14�����炢�Ŕj�]����̂Ŏg��Ȃ�
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
	
	//�K�悷��
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
