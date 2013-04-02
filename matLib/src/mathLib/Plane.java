package mathLib;
/**
 * @author kamakiri01
 * @version  0.81 26 November 2012
 *
 */

/**
 * �R������ԏ�̕��ʃN���X�B<br>
 * �S�����̕��ʃx�N�g���Ƃ���𕪉������@���x�N�g���A���_����̋��������B<br>
 * ���ʃx�N�g���A�@���x�N�g���A���_����̋����͑S��final�����B
 * 
 * @see mathLib.Line
 * @see mathLib.Ray
 *
 */
public class Plane{
	
	/**
	 * ���ʃx�N�g���̗v�f�̎��́in������Ԃ̕��ʂȂ�n�̗v�f�����j
	 */
	protected final double[][] value;
	/**
	 * ���ʂ̖@���x�N�g���B<br>
	 * value�̑O��3�v�f��؂������l�ɂ��x�N�g���B<br>
	 * �@���v�f�͒P�ʒ��x�N�g���������
	 */
	protected final Vector normal;
	/**
	 * ���ʂ�����Ԃ̌��_����̋����B<br>
	 * value�̖����l��؂������v�f�B<br>
	 * �x�N�g���͒P�ʒ��������
	 */
	protected final double d;
	
	/**
	 * �@���x�N�g���ƕ��ʏ�̈�_�������ʂ̃R���X�g���N�^�B<br>
	 * 4�����ȏ�̋�Ԃɂ����镽�ʕ\���ɂ͑Ή����Ă��Ȃ�
	 * @param normal
	 * ���ʂ̖@���x�N�g��
	 * @param anchor
	 * ���ʏ�̔C�ӂ̈�_�B�@���x�N�g���Ƃ͈قȂ�x�N�g���łȂ��Ă͂Ȃ�Ȃ�
	 */
	public Plane (Vector normal, Vector anchor){
		if(normal.m != anchor.m){
			throw new RuntimeException("�����̎�������v���܂���");
		}else if(normal.m != 3){
			throw new RuntimeException("���̃R���X�g���N�^��3�����ȊO�ŕ��ʂ��`�ł��܂���");
		}else if(normal.eq(anchor)){
			throw new RuntimeException("�@���ƕ��ʏ�̈�_�͓���ł͒�`�ł��܂���");
		}
		
		this.normal = normal.toCol().normalize();// test
		this.d = normal.dotProduct(anchor);
		
		this.value = this.normal.value;
		this.value[this.normal.value.length][0] = this.d;
	}
	
	/**
	 * d���P�Ɍ��肵�����ʂ̃R���X�g���N�^�B
	 * ax+by+cz=1
	 * @param a
	 * ���ʂ̕������ɂ�����x�̌W��
	 * @param b
	 * ���ʂ̕������ɂ�����y�̌W��
 	 * @param c
	 * ���ʂ̕������ɂ�����z�̌W��
	 * 
	 */
	public Plane (double a, double b, double c){
		
		this(
				new double[][] {{a},{b},{c},{1}}
						);
		
//		double[][] tmp1 = new double[4][1];
//		tmp1[0][0] = a;
//		tmp1[1][0] = b;
//		tmp1[2][0] = c;
//		tmp1[3][0] = 1;
//		this(tmp1);
		
//		double[][] tmp2 = new double[3][1];
//		tmp2[0][0] = a;
//		tmp2[1][0] = b;
//		tmp2[2][0] = c;
//		this.normal = new Vector(tmp2);
//		this.d = 1;
	}
	
	/**
	 * ���ʃx�N�g����2�����z���^���ĕ��ʂ����R���X�g���N�^
	 * 
	 * @param source
	 * ���ʃx�N�g���̎��̂ƂȂ�񎟌��z��
	 * 
	 */
	public Plane (double[][] source){
		if(source.length != 4){
			throw new RuntimeException("���̃R���X�g���N�^��3�����ȊO�ŕ��ʂ��`�ł��܂���");
		}
		double[][] tmp1 = new double[3][1];
		tmp1[0][0] = source[0][0];
		tmp1[1][0] = source[1][0];
		tmp1[2][0] = source[2][0];
		
		Vector tmp1v = new Vector(tmp1);
		
		double[][] tmp2 = tmp1v.normalize().value; //test
		
		this.value = new double[4][1];
		this.value[0][0] = tmp2[0][0];
		this.value[1][0] = tmp2[1][0];
		this.value[2][0] = tmp2[2][0];
		this.value[3][0] = source[3][0];
//		
//		double[][] tmp = new double[3][1];
//		tmp[0][0] = source[0][0];
//		tmp[1][0] = source[1][0];
//		tmp[2][0] = source[2][0];
		
		this.normal = tmp1v;//new Vector(tmp);
		this.d = source[source.length-1][0];
	}
	
	/**
	 * ���ʃx�N�g����^���ĕ��ʂ����R���X�g���N�^
	 * 
	 * @param source
	 * ���ʃx�N�g��
	 * 
	 */
	public Plane (Vector source){
		this(source.value);
	}
	
	/**
	 * ���ʂ�^���ăR�s�[���ʂ����R���X�g���N�^(�f�B�[�v�R�s�[)
	 * 
	 * @param source
	 * �R�s�[������
	 * 
	 */
	public Plane (Plane source){
		this(source.value);
	}
	
	/**
	 * ��ԏ�̔C�ӂ̓_���畽�ʂ܂ł̋�����Ԃ�
	 * @param dot
	 * ��ԏ�̔C�ӂ̓_�x�N�g��
	 * @return
	 * �����l
	 */
	public double distanceFromDot(Vector dot){
		if(this.normal.m != dot.m){
			throw new RuntimeException("�����̎�������v���܂���");
		}
		double result = this.normal.dotProduct(dot) / this.normal.size();
		return result;
	}
	/**
	 * ���ʃx�N�g���̗v�f��String�^�ɕϊ����ĕԂ�
	 */
	public void printPlane(){
		Vector tmp = new Vector(this.value);
		tmp.printMatrix();
	}
	
	/**
	 * ���ʃx�N�g���̗v�f��String�^�ɕϊ����ĕԂ�
	 */
	public String toString(){
		return this.value.toString();
	}
}