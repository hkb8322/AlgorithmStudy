/*
	1���� n���� ��ȣ�� �پ��ִ� n���� ����� ���� �����ձ⸦ �ϰ� �ֽ��ϴ�. ���� �����ձ�� ������ ���� ��Ģ���� ����˴ϴ�.

	1������ ��ȣ ������� �� ����� ���ʴ�� �ܾ ���մϴ�.
	������ ����� �ܾ ���� �������� �ٽ� 1������ �����մϴ�.
	�ջ���� ���� �ܾ��� ������ ���ڷ� �����ϴ� �ܾ ���ؾ� �մϴ�.
	������ �����ߴ� �ܾ�� ����� �� �����ϴ�.
	�� ������ �ܾ�� �������� �ʽ��ϴ�.
	������ 3���� �����ձ⸦ �ϴ� ��Ȳ�� ��Ÿ���ϴ�.

	tank �� kick �� know �� wheel �� land �� dream �� mother �� robot �� tank

	�� �����ձ�� ������ ���� ����˴ϴ�.

	1�� ����� �ڽ��� ù ��° ���ʿ� tank�� ���մϴ�.
	2�� ����� �ڽ��� ù ��° ���ʿ� kick�� ���մϴ�.
	3�� ����� �ڽ��� ù ��° ���ʿ� know�� ���մϴ�.
	1�� ����� �ڽ��� �� ��° ���ʿ� wheel�� ���մϴ�.
	(��� ����)
	�����ձ⸦ ��� ������ ������ ����, 3�� ����� �ڽ��� �� ��° ���ʿ� ���� tank ��� �ܾ�� ������ �����ߴ� �ܾ��̹Ƿ� Ż���ϰ� �˴ϴ�.

	����� �� n�� ������� ������� ���� �ܾ� words �� �Ű������� �־��� ��, ���� ���� Ż���ϴ� ����� ��ȣ�� �� ����� �ڽ��� �� ��° ���ʿ� Ż���ϴ����� ���ؼ� return �ϵ��� solution �Լ��� �ϼ����ּ���.

	���� ����
	�����ձ⿡ �����ϴ� ����� �� n�� 2 �̻� 10 ������ �ڿ����Դϴ�.
	words�� �����ձ⿡ ����� �ܾ���� ������� ����ִ� �迭�̸�, ���̴� n �̻� 100 �����Դϴ�.
	�ܾ��� ���̴� 2 �̻� 50 �����Դϴ�.
	��� �ܾ�� ���ĺ� �ҹ��ڷθ� �̷���� �ֽ��ϴ�.
	�����ձ⿡ ���Ǵ� �ܾ��� ��(�ǹ�)�� �Ű� ���� �����ŵ� �˴ϴ�.
	������ [ ��ȣ, ���� ] ���·� return ���ּ���.
	���� �־��� �ܾ��� Ż���ڰ� ������ �ʴ´ٸ�, [0, 0]�� return ���ּ���.
*/
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0}; // ���� ���� Ż���ϴ� ����� ��ȣ, �� ��° ���ʿ� Ż���ϴ���
        String[] duplicate = new String[words.length]; // �ߺ�üũ�� �迭
        String startChar = ""; // �����ؾ� �ϴ� ����
        
        for(int i = 0 ; i < words.length ; i++) {
            boolean bFail = true; // Ż�� ����
            
            if(i > 0) {
                // ù �ܾ �ƴ� ���
                if(!startChar.isEmpty())  {
                    // �� ���� �̻�����
                    if(words[i].length() > 1) {
                        // ������ ���ڷ� �����ϴ���
                        if(words[i].startsWith(startChar)) {
                            // ������ �ߺ��� �ܾ �ִ���
                            if(!chkDuplicate(duplicate, words[i])) {
                                bFail = false;
                            }
                        }
                    }
                }
                
                if(bFail) {
                    System.out.println(i + ", " + words[i]);
                    answer[0] = i % n + 1;
                    answer[1] = i / n + 1;
                    System.out.println(answer[0] + ", " + answer[1]);
                    break;
                }
            }
              
            // ������ ���� ����
            startChar = words[i].substring(words[i].length() - 1);

            // �ߺ�üũ�� �迭�� ����
            duplicate[i] = words[i]; 
        }

        return answer;
    }
    
    // �迭 �� �ش� ���� �ִ��� Ȯ��
    public boolean chkDuplicate(String[] arr, String chkStr) {
        boolean bExist = false;
        
        for(String tmpStr : arr) {
            if(chkStr.equals(tmpStr)) {
                bExist = true;
            }
        }
        
        return bExist;
    }
}