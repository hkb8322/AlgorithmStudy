/*
셔틀버스
카카오에서는 무료 셔틀버스를 운행하기 때문에 판교역에서 편하게 사무실로 올 수 있다. 카카오의 직원은 서로를 '크루'라고 부르는데, 아침마다 많은 크루들이 이 셔틀을 이용하여 출근한다.

이 문제에서는 편의를 위해 셔틀은 다음과 같은 규칙으로 운행한다고 가정하자.

셔틀은 09:00부터 총 n회 t분 간격으로 역에 도착하며, 하나의 셔틀에는 최대 m명의 승객이 탈 수 있다.
셔틀은 도착했을 때 도착한 순간에 대기열에 선 크루까지 포함해서 대기 순서대로 태우고 바로 출발한다. 예를 들어 09:00에 도착한 셔틀은 자리가 있다면 09:00에 줄을 선 크루도 탈 수 있다.
일찍 나와서 셔틀을 기다리는 것이 귀찮았던 콘은, 일주일간의 집요한 관찰 끝에 어떤 크루가 몇 시에 셔틀 대기열에 도착하는지 알아냈다. 콘이 셔틀을 타고 사무실로 갈 수 있는 도착 시각 중 제일 늦은 시각을 구하여라.

단, 콘은 게으르기 때문에 같은 시각에 도착한 크루 중 대기열에서 제일 뒤에 선다. 또한, 모든 크루는 잠을 자야 하므로 23:59에 집에 돌아간다. 따라서 어떤 크루도 다음날 셔틀을 타는 일은 없다.

입력 형식
셔틀 운행 횟수 n, 셔틀 운행 간격 t, 한 셔틀에 탈 수 있는 최대 크루 수 m, 크루가 대기열에 도착하는 시각을 모은 배열 timetable이 입력으로 주어진다.

0 ＜ n ≦ 10
0 ＜ t ≦ 60
0 ＜ m ≦ 45
timetable은 최소 길이 1이고 최대 길이 2000인 배열로, 하루 동안 크루가 대기열에 도착하는 시각이 HH:MM 형식으로 이루어져 있다.
크루의 도착 시각 HH:MM은 00:01에서 23:59 사이이다.
출력 형식
콘이 무사히 셔틀을 타고 사무실로 갈 수 있는 제일 늦은 도착 시각을 출력한다. 도착 시각은 HH:MM 형식이며, 00:00에서 23:59 사이의 값이 될 수 있다.

입출력 예제
n	t	m	timetable	answer
1	1	5	[08:00, 08:01, 08:02, 08:03]	09:00
2	10	2	[09:10, 09:09, 08:00]	09:09
2	1	2	[09:00, 09:00, 09:00, 09:00]	08:59
1	1	5	[00:01, 00:01, 00:01, 00:01, 00:01]	00:00
1	1	1	[23:59]	09:00
10	60	45	[23:59,23:59, 23:59, 23:59, 23:59, 23:59, 23:59, 23:59, 23:59, 23:59, 23:59, 23:59, 23:59, 23:59, 23:59, 23:59]	18:00

https://programmers.co.kr/learn/courses/30/lessons/17678
*/
import java.util.*;

class Solution {
  public String solution(int n, int t, int m, String[] timetable) {
      String answer = "";
      String crewTime = "";
      int idx = 0;
      
      Arrays.sort(timetable);
      Calendar cShuttle = getCalendar("09:00");
      
      for(int i = 0 ; i < n ; i++) {
          int remainCnt = m;
          
          for(int j = 0 ; j < m ; j++, idx++) {
              if(idx < timetable.length) {
                  crewTime = timetable[idx];
                  Calendar cCrew = getCalendar(crewTime);

                  if(cShuttle.compareTo(cCrew) == -1) {
                      break;
                  }
                  else {
                      remainCnt--;
                  }
              }
              else {
                  break;
              }
          }
          
          // 마지막 셔틀인 경우 콘이 도착해야 하는 시간 계산
          if(i == (n - 1)) {
              if(remainCnt  == 0) {
                  Calendar cCrew = getCalendar(crewTime);
                  cCrew.add(Calendar.MINUTE, -1);
                  answer = getTimeString(cCrew);
              }
              else {
                  answer = getTimeString(cShuttle);
              }
          }
          
          // 셔틀 시간 증가
          cShuttle.add(Calendar.MINUTE, t);
      }
      
      return answer;
  }
  
  // 캘린더 객체 리턴
  public Calendar getCalendar(String pTime) {
      String[] time = pTime.split(":");
      
      Date date = new Date();
      date.setHours(Integer.parseInt(time[0]));
      date.setMinutes(Integer.parseInt(time[1]));
      
      Calendar cald = Calendar.getInstance();
      cald.setTime(date);
      cald.set(Calendar.SECOND, 0);
	  cald.set(Calendar.MILLISECOND, 0);
      
      return cald;
  }
    
  // 시간 포맷의 문자열 리턴
  public String getTimeString(Calendar pCalendar) {
      return String.format("%02d:%02d", pCalendar.get(Calendar.HOUR_OF_DAY), pCalendar.get(Calendar.MINUTE));
  }
}