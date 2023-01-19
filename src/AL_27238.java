import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_27238 {
    static int N, L, M;
    static int[] LMap;
    static int[][] MMap;
    static boolean[][] visitCheck;
    static int ans = 0;

    public static void upperCheck(int start, int end) {
        if(start <= end)
            upperCase(start, end);
        else
            lowerCase(start, end);
    }

    public static void AtoB(int start, int end, int i) {
        if(start <= MMap[i][0] && end >= MMap[i][0]) {
            if(!visitCheck[i][0]) {
                visitCheck[i][0] = true;
                if(visitCheck[i][1]) {
                    ans++;
                    visitCheck[i][1] = false;
                }
            }
        }
        if(start <= MMap[i][1] && end >= MMap[i][1]) {
            if(!visitCheck[i][1]) {
                visitCheck[i][1] = true;
                if(visitCheck[i][0]) {
                    ans++;
                    visitCheck[i][0] = false;
                }
            }
        }
    }

    public static void BtoA(int start, int end, int i) {
        if(start >= MMap[i][1] && end <= MMap[i][1]) {
            if(!visitCheck[i][1]) {
                visitCheck[i][1] = true;
                if(visitCheck[i][0]) {
                    ans++;
                    visitCheck[i][0] = false;
                }
            }
        }
        if(start >= MMap[i][0] && end <= MMap[i][0]) {
            if(!visitCheck[i][0]) {
                visitCheck[i][0] = true;
                if(visitCheck[i][1]) {
                    ans++;
                    visitCheck[i][1] = false;
                }
            }
        }
    }

    public static void upperCase(int start, int end) {
        for(int i=0; i<M; i++) {
            if(start <= MMap[i][0] && end >= MMap[i][0]) {
                if(!visitCheck[i][0]) {
                    visitCheck[i][0] = true;
                    if(visitCheck[i][1]) {
                        ans++;
                        visitCheck[i][1] = false;
                    }
                }
            }
            if(start <= MMap[i][1] && end >= MMap[i][1]) {
                if(!visitCheck[i][1]) {
                    visitCheck[i][1] = true;
                    if(visitCheck[i][0]) {
                        ans++;
                        visitCheck[i][0] = false;
                    }
                }
            }
        }
    }

    public static void lowerCase(int start, int end) {
        for(int i=0; i<M; i++) {
            if(start >= MMap[i][1] && end <= MMap[i][1]) {
                if(!visitCheck[i][1]) {
                    visitCheck[i][1] = true;
                    if(visitCheck[i][0]) {
                        ans++;
                        visitCheck[i][0] = false;
                    }
                }
            }
            if(start >= MMap[i][0] && end <= MMap[i][0]) {
                if(!visitCheck[i][0]) {
                    visitCheck[i][0] = true;
                    if(visitCheck[i][1]) {
                        ans++;
                        visitCheck[i][1] = false;
                    }
                }
            }
        }

    }



    public static void check(int start, int end) {
        if(start <= end)
            asc(start, end);
        else
            dsc(start, end);
    }

    public static void asc(int start, int end) {
        for(int i=start; i<=end; i++) {
            for(int j=0; j< MMap.length; j++) {
                if(i == MMap[j][0]) {
                    if(!visitCheck[j][0]) {
                        visitCheck[j][0] = true;
                        if(visitCheck[j][1]) {
                            visitCheck[j][1] = false;
                            ans++;
                        }
                    }
                }
                else if(i == MMap[j][1]) {
                    if(!visitCheck[j][1]) {
                        visitCheck[j][1] = true;
                        if(visitCheck[j][0]) {
                            visitCheck[j][0] = false;
                            ans++;
                        }
                    }
                }
            }
        }
    }

    public static void dsc(int start, int end) {
        for(int i=start; i>=end; i--) {
            for(int j=0; j< MMap.length; j++) {
                if(i == MMap[j][0]) {
                    if(!visitCheck[j][0]) {
                        visitCheck[j][0] = true;
                        if(visitCheck[j][1]) {
                            visitCheck[j][1] = false;
                            ans++;
                        }
                    }
                }
                else if(i == MMap[j][1]) {
                    if(!visitCheck[j][1]) {
                        visitCheck[j][1] = true;
                        if(visitCheck[j][0]) {
                            visitCheck[j][0] = false;
                            ans++;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        LMap = new int[L];
        MMap = new int[M][2];
        visitCheck = new boolean[M][2];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<L; i++) {
            LMap[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            MMap[i][0] = Integer.parseInt(st.nextToken());
            MMap[i][1] = Integer.parseInt(st.nextToken());
        }


        for(int i=0; i<L-1; i++) {
            check(LMap[i], LMap[i+1]);
            System.out.println(ans);
        }
        check(LMap[L-1], LMap[0]);
        System.out.println(ans);
    }
}
