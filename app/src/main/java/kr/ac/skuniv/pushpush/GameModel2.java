package kr.ac.skuniv.pushpush;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GameModel2 {
    /*********************************************************************************************/
    // 멤버 변수
    private String nameUser;  // 사용자 name
    private int characterUser;  // 사용자 character
    private int stageNum = 0;  // stage 번호
    private int movesNum = 0;  // moves 횟수(움직인 횟수)
    private int timeNum = 0;  // time
    private int[][][] mapArr =  // [MAP_STAGE][MAP_COL][MAP_ROW] 인 map 배열 선언
            {
                    {
                            {0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
                            {0, 0, 1, 2, 2, 2, 2, 1, 1, 1},
                            {1, 1, 1, 2, 2, 2, 2, 5, 2, 1},
                            {1, 2, 2, 2, 2, 4, 2, 2, 2, 1},
                            {1, 3, 2, 2, 2, 2, 4, 2, 2, 1},
                            {1, 2, 2, 2, 2, 2, 2, 2, 2, 1},
                            {1, 2, 2, 2, 2, 2, 2, 3, 2, 1},
                            {1, 2, 2, 1, 1, 1, 1, 2, 2, 1},
                            {1, 2, 8, 1, 0, 0, 1, 2, 2, 1},
                            {1, 1, 1, 1, 0, 0, 1, 1, 1, 1}
                    },
                    {
                            {0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
                            {1, 1, 1, 2, 2, 2, 2, 1, 1, 0},
                            {1, 3, 2, 4, 2, 2, 2, 5, 1, 1},
                            {1, 1, 2, 2, 2, 2, 2, 2, 2, 1},
                            {0, 1, 2, 2, 2, 2, 4, 2, 2, 1},
                            {0, 1, 2, 4, 2, 2, 2, 2, 2, 1},
                            {1, 1, 2, 2, 2, 2, 2, 2, 1, 1},
                            {1, 3, 2, 2, 2, 2, 2, 2, 1, 0},
                            {1, 1, 8, 2, 1, 1, 1, 3, 1, 0},
                            {0, 1, 1, 1, 1, 0, 1, 1, 1, 0}
                    },
                    {
                            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                            {0, 1, 1, 1, 3, 1, 1, 1, 1, 1},
                            {0, 1, 2, 2, 2, 2, 2, 2, 5, 1},
                            {0, 1, 2, 4, 2, 2, 2, 2, 2, 1},
                            {1, 1, 2, 2, 4, 2, 2, 2, 2, 1},
                            {1, 3, 2, 2, 2, 2, 2, 4, 2, 1},
                            {1, 1, 2, 2, 2, 2, 2, 2, 2, 1},
                            {1, 1, 2, 2, 2, 2, 4, 2, 2, 1},
                            {1, 3, 8, 2, 1, 1, 1, 1, 3, 1},
                            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1}
                    }
            };
    private  int[][][] saveMap =  // [MAP_STAGE][MAP_COL][MAP_ROW] 인 이전 상태를 저장할 맵 배열 선언
            {
                    {
                            {0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
                            {0, 0, 1, 2, 2, 2, 2, 1, 1, 1},
                            {1, 1, 1, 2, 2, 2, 2, 5, 2, 1},
                            {1, 2, 2, 2, 2, 4, 2, 2, 2, 1},
                            {1, 3, 2, 2, 2, 2, 4, 2, 2, 1},
                            {1, 2, 2, 2, 2, 2, 2, 2, 2, 1},
                            {1, 2, 2, 2, 2, 2, 2, 3, 2, 1},
                            {1, 2, 2, 1, 1, 1, 1, 2, 2, 1},
                            {1, 2, 8, 1, 0, 0, 1, 2, 2, 1},
                            {1, 1, 1, 1, 0, 0, 1, 1, 1, 1}
                    },
                    {
                            {0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
                            {1, 1, 1, 2, 2, 2, 2, 1, 1, 0},
                            {1, 3, 2, 4, 2, 2, 2, 5, 1, 1},
                            {1, 1, 2, 2, 2, 2, 2, 2, 2, 1},
                            {0, 1, 2, 2, 2, 2, 4, 2, 2, 1},
                            {0, 1, 2, 4, 2, 2, 2, 2, 2, 1},
                            {1, 1, 2, 2, 2, 2, 2, 2, 1, 1},
                            {1, 3, 2, 2, 2, 2, 2, 2, 1, 0},
                            {1, 1, 8, 2, 1, 1, 1, 3, 1, 0},
                            {0, 1, 1, 1, 1, 0, 1, 1, 1, 0}
                    },
                    {
                            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                            {0, 1, 1, 1, 3, 1, 1, 1, 1, 1},
                            {0, 1, 2, 2, 2, 2, 2, 2, 5, 1},
                            {0, 1, 2, 4, 2, 2, 2, 2, 2, 1},
                            {1, 1, 2, 2, 4, 2, 2, 2, 2, 1},
                            {1, 3, 2, 2, 2, 2, 2, 4, 2, 1},
                            {1, 1, 2, 2, 2, 2, 2, 2, 2, 1},
                            {1, 1, 2, 2, 2, 2, 4, 2, 2, 1},
                            {1, 3, 8, 2, 1, 1, 1, 1, 3, 1},
                            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1}
                    }
            };
    private int characterX, characterY;  // character의 x(행), y(열) 좌표(index 값)
    private int ghostX, ghostY;  // ghost의 x(행), y(열) 좌표(index 값)
    public final int mapX = 5;  // map의 시작 x 좌표값
    public final int mapY = 470;  // map의 시작 y 좌표값
    public final int mapWidth = 130;  // map의 배열 1칸의 너비
    public final int mapHeight = 130;  // map의 배열 1칸의 높이
    public final int MAX_MAP_STAGE = 2;  // 최대 stage
    public final int MAP_ROW = 10;  // map의 row
    public final int MAP_COL = 10;  // map의 col
    public final int OUTSIDE_IMG = 0;  // 벽돌 바깥
    public final int WALL_IMG = 1;  // 벽돌
    public final int INSIDE_IMG = 2;  // 벽돌 안쪽
    public final int GOAL_IMG = 3;  // 도착지
    public final int BOX_IMG = 4;  // 박스
    public final int CHARACTER_IMG = 5;  // 케릭터
    public final int GOAL_BOX_IMG = 6;  // 도착지 + 박스
    public final int GOAL_CHARACTER_IMG = 7;  // 도착지 + 케릭터
    public final int GHOST_IMG = 8;  // 유령
    public final int GOAL_GHOST_IMG = 9;  // 유령 + 도착지
    public final int LEFT_KEY = 0;  // LEFT_KEY
    public final int RIGHT_KEY = 1;  // RIGHT_KEY
    public final int UP_KEY = 2;  // UP_KEY
    public final int DOWN_KEY = 3;  // DOWN_KEY
    /*********************************************************************************************/
    // getter & setter
    public String getNameUser() { return nameUser; }
    public void setNameUser(String nameUser) { this.nameUser = nameUser; }
    public int getCharacterUser() { return characterUser; }
    public void setCharacterUser(int characterUser) { this.characterUser = characterUser; }
    public int getStageNum() { return stageNum; }
    public void setStageNum(int stageNum) { this.stageNum = stageNum; }
    public int getMovesNum() { return movesNum; }
    public void setMovesNum(int movesNum) { this.movesNum = movesNum; }
    public int getTimeNum() { return timeNum; }
    public void setTimeNum(int timeNum) { this.timeNum = timeNum; }
    public int[][][] getMapArr() { return mapArr; }
    public void setMapArr(int[][][] mapArr) { this.mapArr = mapArr; }
    public int getMapArr(int x, int y) { return mapArr[stageNum][x][y]; }
    public void setMapArr(int x, int y, int value) { mapArr[stageNum][x][y] = value; }
    public int[][][] getSavemap() { return saveMap; }
    public void setSaveMap(int[][][] saveMap) { this.saveMap = saveMap; }
    public int getSaveMap(int x, int y) { return saveMap[stageNum][x][y]; }
    public void setSaveMap(int x, int y, int value) { saveMap[stageNum][x][y] = value; }
    public void setSaveMap() {
        for (int i = 0 ; i < MAP_COL; i++)
            for (int j = 0 ; j < MAP_ROW; j++)
                setSaveMap(i, j, getMapArr(i, j));
    }
    public void setCharacterXY() {
        for (int i = 0 ; i < MAP_COL; i++)
            for (int j = 0 ; j < MAP_ROW; j++)
                if (mapArr[stageNum][i][j] == CHARACTER_IMG || mapArr[stageNum][i][j] == GOAL_CHARACTER_IMG) {
                    characterX = i;
                    characterY = j;
                }
    }
    public int getCharacterX() { return characterX; }
    public int getCharacterY() { return characterY; }
    public void setGhostXY() {
        for (int i = 0 ; i < MAP_COL; i++)
            for (int j = 0 ; j < MAP_ROW; j++)
                if (mapArr[stageNum][i][j] == GHOST_IMG || mapArr[stageNum][i][j] == GOAL_GHOST_IMG) {
                    ghostX = i;
                    ghostY = j;
                }
    }
    public int getGhostX() { return ghostX; }
    public void setGhostX(int ghostX) { this.ghostX = ghostX; }
    public int getGhostY() { return ghostY; }
    public void setGhostY(int ghostY) { this.ghostY = ghostY; }
    /*********************************************************************************************/
    // character가 움직일 수 있는지 check하는 메소드
    public boolean checkPossibleMove(int key, int x, int y) {
        boolean result = true;
        switch(key) {
            case LEFT_KEY:
                if(mapArr[stageNum][x][y - 1] == WALL_IMG) result = false;
                else if(mapArr[stageNum][x][y - 1] == BOX_IMG || mapArr[stageNum][x][y - 1] == GOAL_BOX_IMG) {
                    if (mapArr[stageNum][x][y - 2] == WALL_IMG || mapArr[stageNum][x][y - 2] == BOX_IMG || mapArr[stageNum][x][y - 2] == GOAL_BOX_IMG)
                        result = false;
                }
                else result = true;
                break;
            case RIGHT_KEY:
                if(mapArr[stageNum][x][y + 1] == WALL_IMG) result = false;
                else if(mapArr[stageNum][x][y + 1] == BOX_IMG || mapArr[stageNum][x][y + 1] == GOAL_BOX_IMG) {
                    if (mapArr[stageNum][x][y + 2] == WALL_IMG || mapArr[stageNum][x][y + 2] == BOX_IMG || mapArr[stageNum][x][y + 2] == GOAL_BOX_IMG)
                        result = false;
                }
                else result = true;
                break;
            case UP_KEY:
                if(mapArr[stageNum][x - 1][y] == WALL_IMG) result = false;
                else if(mapArr[stageNum][x - 1][y] == BOX_IMG || mapArr[stageNum][x - 1][y] == GOAL_BOX_IMG) {
                    if (mapArr[stageNum][x - 2][y] == WALL_IMG || mapArr[stageNum][x - 2][y] == BOX_IMG || mapArr[stageNum][x - 2][y] == GOAL_BOX_IMG)
                        result = false;
                }
                else result = true;
                break;
            case DOWN_KEY:
                if(mapArr[stageNum][x + 1][y] == WALL_IMG) result = false;
                else if(mapArr[stageNum][x + 1][y] == BOX_IMG || mapArr[stageNum][x + 1][y] == GOAL_BOX_IMG) {
                    if (mapArr[stageNum][x + 2][y] == WALL_IMG || mapArr[stageNum][x + 2][y] == BOX_IMG || mapArr[stageNum][x + 2][y] == GOAL_BOX_IMG)
                        result = false;
                }
                else result = true;
                break;
        }
        return result;
    }
    /*********************************************************************************************/
    // keybutton을 눌렀을때 Key 값과 CharacterX, CharacterY를 받아와 mapArr을 update 하는 메소드
    public void updateMap(int key, int x, int y) {
        switch(key) {
            case LEFT_KEY:
                if (getMapArr(x, y - 1) == INSIDE_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x, y - 1, CHARACTER_IMG);
                }
                else if (getMapArr(x, y - 1) == BOX_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x, y - 1, CHARACTER_IMG);
                    if (getMapArr(x, y - 2) == INSIDE_IMG) setMapArr(x, y - 2, BOX_IMG);
                    else if (getMapArr(x, y - 2) == GOAL_IMG) setMapArr(x, y - 2, GOAL_BOX_IMG);
                }
                else if (getMapArr(x, y - 1) == GOAL_BOX_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x, y - 1, GOAL_CHARACTER_IMG);
                    if (getMapArr(x, y - 2) == INSIDE_IMG) setMapArr(x, y - 2, BOX_IMG);
                    else if (getMapArr(x, y - 2) == GOAL_IMG) setMapArr(x, y - 2, GOAL_BOX_IMG);
                }
                else if (getMapArr(x, y - 1) == GOAL_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x, y - 1, GOAL_CHARACTER_IMG);
                }
                break;
            case RIGHT_KEY:
                if (getMapArr(x, y + 1) == INSIDE_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x, y + 1, CHARACTER_IMG);
                }
                else if (getMapArr(x, y + 1) == BOX_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x, y + 1, CHARACTER_IMG);
                    if (getMapArr(x, y + 2) == INSIDE_IMG) setMapArr(x, y + 2, BOX_IMG);
                    else if (getMapArr(x, y + 2) == GOAL_IMG) setMapArr(x, y + 2, GOAL_BOX_IMG);
                }
                else if (getMapArr(x, y + 1) == GOAL_BOX_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x, y + 1, GOAL_CHARACTER_IMG);
                    if (getMapArr(x, y + 2) == INSIDE_IMG) setMapArr(x, y + 2, BOX_IMG);
                    else if (getMapArr(x, y + 2) == GOAL_IMG) setMapArr(x, y + 2, GOAL_BOX_IMG);
                }
                else if (getMapArr(x, y + 1) == GOAL_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x, y + 1, GOAL_CHARACTER_IMG);
                }
                break;
            case UP_KEY:
                if (getMapArr(x - 1, y) == INSIDE_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x - 1, y, CHARACTER_IMG);
                }
                else if (getMapArr(x - 1, y) == BOX_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x - 1, y, CHARACTER_IMG);
                    if (getMapArr(x - 2, y) == INSIDE_IMG) setMapArr(x - 2, y, BOX_IMG);
                    else if (getMapArr(x - 2, y) == GOAL_IMG) setMapArr(x - 2, y, GOAL_BOX_IMG);
                }
                else if (getMapArr(x - 1, y) == GOAL_BOX_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x - 1, y, GOAL_CHARACTER_IMG);
                    if (getMapArr(x - 2, y) == INSIDE_IMG) setMapArr(x - 2, y, BOX_IMG);
                    else if (getMapArr(x - 2, y) == GOAL_IMG) setMapArr(x - 2, y, GOAL_BOX_IMG);
                }
                else if (getMapArr(x - 1, y) == GOAL_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x - 1, y, GOAL_CHARACTER_IMG);
                }
                break;
            case DOWN_KEY:
                if (getMapArr(x + 1, y) == INSIDE_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x + 1, y, CHARACTER_IMG);
                }
                else if (getMapArr(x + 1, y) == BOX_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x + 1, y, CHARACTER_IMG);
                    if (getMapArr(x + 2, y) == INSIDE_IMG) setMapArr(x + 2, y, BOX_IMG);
                    else if (getMapArr(x + 2, y) == GOAL_IMG) setMapArr(x + 2, y, GOAL_BOX_IMG);
                }
                else if (getMapArr(x + 1, y) == GOAL_BOX_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x + 1, y, GOAL_CHARACTER_IMG);
                    if (getMapArr(x + 2, y) == INSIDE_IMG) setMapArr(x + 2, y, BOX_IMG);
                    else if (getMapArr(x + 2, y) == GOAL_IMG) setMapArr(x + 2, y, GOAL_BOX_IMG);
                }
                else if (getMapArr(x + 1, y) == GOAL_IMG) {
                    if (getMapArr(x, y) == CHARACTER_IMG) setMapArr(x, y, INSIDE_IMG);
                    else if (getMapArr(x, y) == GOAL_CHARACTER_IMG) setMapArr(x, y, GOAL_IMG);
                    setMapArr(x + 1, y, GOAL_CHARACTER_IMG);
                }
                break;
        }
    }
    /*********************************************************************************************/
    // GOAL_BOX_IMG가 몇개있는지 check하여 return 해주는 메소드
    public int checkGoalBox() {
        int cnt = 0;
        for (int i = 0 ; i < MAP_COL; i++)
            for (int j = 0 ; j < MAP_ROW; j++)
                if (mapArr[stageNum][i][j] == GOAL_BOX_IMG) {
                    cnt++;
                }
        return cnt;
    }
    /*********************************************************************************************/
    // grade를 check해서 return하는 메소드
    public char checkGrade(int stageNum, int movesNum, int timeNum) {
        char result = 0;
        switch (stageNum) {
            case 0:
                if (movesNum <= 20 && timeNum <= 10)
                    result = 'A';
                else if (movesNum <= 23 && timeNum <= 30)
                    result = 'B';
                else if (movesNum <= 26 && timeNum <= 60)
                    result = 'C';
                else if (movesNum <= 29 && timeNum <= 90)
                    result = 'D';
                else if (movesNum > 29 && timeNum < 120)
                    result = 'F';
                break;
            case 1:
                if (movesNum <= 31 && timeNum <= 10)
                    result = 'A';
                else if (movesNum <= 34 && timeNum <= 30)
                    result = 'B';
                else if (movesNum <= 37 && timeNum <= 60)
                    result = 'C';
                else if (movesNum <= 40 && timeNum <= 90)
                    result = 'D';
                else if (movesNum > 40 && timeNum < 120)
                    result = 'F';
                break;
            case 2:
                if (movesNum <= 38 && timeNum <= 10)
                    result = 'A';
                else if (movesNum <= 41 && timeNum <= 30)
                    result = 'B';
                else if (movesNum <= 44 && timeNum <= 60)
                    result = 'C';
                else if (movesNum <= 47 && timeNum <= 90)
                    result = 'D';
                else if (movesNum > 47 && timeNum < 120)
                    result = 'F';
                break;
        }
        return result;
    }
    /*********************************************************************************************/
    // 이전의 map이 저장된 SaveMap을 현재 Map에 적용하고 movesNum을 1 감소하는 메소드
    public void backMap() {
        for (int i = 0 ; i < MAP_COL; i++)
            for (int j = 0 ; j < MAP_ROW; j++)
                setMapArr(i, j, getSaveMap(i, j));
        movesNum -= 1;
    }
    /*********************************************************************************************/
    // ghost가 character의 위치 까지 최단 경로를 계산하는 메소드
    public void bfs(int map[][][]) {
        boolean visited[][] = new boolean[MAP_COL][MAP_ROW];
        int move_map[][] = new int[MAP_COL][MAP_ROW];
        Queue<Integer> q_x = new LinkedList<Integer>();
        Queue<Integer> q_y = new LinkedList<Integer>();
        int dx[] = { 1, -1, 0, 0 };
        int dy[] = { 0, 0, 1, -1 };
        q_x.offer(ghostX);
        q_y.offer(ghostY);
        move_map[ghostX][ghostY] = 1;
        while (!q_x.isEmpty()) {
            int x = q_x.poll();
            int y = q_y.poll();
            if (x == characterX && y == characterY) break;
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int move_x = x + dx[i];
                int move_y = y + dy[i];
                if (move_x >= 0 && move_x < MAP_COL && move_y >= 0 && move_y < MAP_ROW && !visited[move_x][move_y]
                        && (map[stageNum][move_x][move_y] == 2 || map[stageNum][move_x][move_y] == 5)) {
                    q_x.offer(move_x);
                    q_y.offer(move_y);
                    move_map[move_x][move_y] = move_map[x][y] + 1;
                }
            }
        }
        findRoute(move_map);
    }
    /*********************************************************************************************/
    // bfs 메소드에서 계산된 최단 경로를 역추적하여 ghost의 다음 경로를 지정해주는 메소드
    public void findRoute(int move_map[][]) {
        Stack<Integer> x_stack = new Stack<Integer>();
        Stack<Integer> y_stack = new Stack<Integer>();
        x_stack.push(characterX);
        y_stack.push(characterY);
        int x = characterX;
        int y = characterY;
        int dx[] = {1, -1, 0, 0};
        int dy[] = {0, 0, 1, -1};
        while (move_map[x][y] != 1) {
            for (int i = 0 ; i < 4; i ++) {
                int move_x = x + dx[i];
                int move_y = y + dy[i];
                if (move_x >= 0 && move_y >= 0 && move_x < MAP_COL && move_y < MAP_ROW && move_map[move_x][move_y] == move_map[x][y] - 1) {
                    x_stack.push(move_x);
                    y_stack.push(move_y);
                    x = move_x;
                    y = move_y;
                    break;
                }
            }
        }
        x_stack.pop();
        y_stack.pop();
        setMapArr(ghostX, ghostY, INSIDE_IMG);
        ghostX = x_stack.pop();
        ghostY = y_stack.pop();
        setMapArr(ghostX, ghostY, GHOST_IMG);
    }
    /*********************************************************************************************/
    // GameModel2 생성자
    public GameModel2() { }
}
