import java.util.Arrays;

/*

< 작성 코드 >

class Solution {
	public int[] solution(int brown, int yellow) {
		int[] answer = {};
		final int maxHeight = (int) Math.sqrt(yellow);

		for (int h = maxHeight; h > 0; h--) {
			final int w = (int) (yellow / h);
			final int brownWidth = (int) (brown - (h + 2) * 2) / 2;

			if (brownWidth == w) {
				if (w * 2 + h * 2 + 4 != brown)
					continue;

				if (w > h)
					return new int[] { w + 2, h + 2 };

				else
					return new int[] { h + 2, w + 2 };
			}
		}

		return answer;
	}
}

*/

// <다른 풀이 해석>

class Solution {
	public int[] solution(int brown, int yellow) {

		int[] answer = null;
		final int sum = brown + yellow;

		for (int i = 3; i < sum; i++) {
			if(sum % 3 == 0) {
				final int w = i;
				final int h = (int) (sum/i);
				if ((w-2) * (h-2) == yellow)
					answer = new int[] { w, h };
			}
		}

		Arrays.sort(answer);
		return answer;
	}
}

/**
 * 정답은
 * 	1. brown + yellow 만큼 넓이의 직사각형
 * 	2. 모서리를 제외한 [w-2, h-2] 크기의 직사각형 넓이가 yellow
 * 조건을 충족해야한다.
 */