import java.io.*;
import java.util.*;

public class AL_5373 {
    static int T;
    static int n;
    static String lotate;

    public static SideBlock[][][] SideCube = new SideBlock[3][3][3];

    public static class SideBlock {
        Side[] block;

        public SideBlock(Side[] block) {
            this.block = block;
        }
    }

    public static class Side {
        char color;
        int[] dir;

        public Side(char color, int[] dir) {
            this.color = color;
            this.dir = dir;
        }
    }

    public static void CubeInit() {
        // 윗 면
        SideCube[0][0][0] = new SideBlock(new Side[] {new Side('w', new int[]{-1,0,0}), new Side('o', new int[]{0,0,-1}), new Side('g', new int[] {0,-1,0})});
        SideCube[0][0][1] = new SideBlock(new Side[] {new Side('w', new int[]{-1,0,0}), new Side('g', new int[] {0,-1,0})});
        SideCube[0][0][2] = new SideBlock(new Side[] {new Side('w', new int[]{-1,0,0}), new Side('r', new int[]{0,0,1}), new Side('g', new int[] {0,-1,0})});

        SideCube[0][1][0] = new SideBlock(new Side[] {new Side('w', new int[]{-1,0,0}), new Side('o', new int[]{0,0,-1})});
        SideCube[0][1][1] = new SideBlock(new Side[] {new Side('w', new int[]{-1,0,0})});
        SideCube[0][1][2] = new SideBlock(new Side[] {new Side('w', new int[]{-1,0,0}), new Side('r', new int[]{0,0,1})});

        SideCube[0][2][0] = new SideBlock(new Side[] {new Side('w', new int[]{-1,0,0}), new Side('o', new int[]{0,0,-1}), new Side('b', new int[] {0,1,0})});
        SideCube[0][2][1] = new SideBlock(new Side[] {new Side('w', new int[]{-1,0,0}), new Side('b', new int[] {0,1,0})});
        SideCube[0][2][2] = new SideBlock(new Side[] {new Side('w', new int[]{-1,0,0}), new Side('r', new int[]{0,0,1}), new Side('b', new int[] {0,1,0})});

        // 윗 면
        
        // 중앙 면
        SideCube[1][0][0] = new SideBlock(new Side[] {new Side('o', new int[]{0,0,-1}), new Side('g', new int[] {0,-1,0})});
        SideCube[1][0][1] = new SideBlock(new Side[] {new Side('g', new int[] {0,-1,0})});
        SideCube[1][0][2] = new SideBlock(new Side[] {new Side('r', new int[]{0,0,1}), new Side('g', new int[] {0,-1,0})});

        SideCube[1][1][0] = new SideBlock(new Side[] {new Side('o', new int[]{0,0,-1})});
        SideCube[1][1][1] = new SideBlock(new Side[] {});
        SideCube[1][1][2] = new SideBlock(new Side[] {new Side('r', new int[]{0,0,1})});

        SideCube[1][2][0] = new SideBlock(new Side[] {new Side('o', new int[]{0,0,-1}), new Side('b', new int[] {0,1,0})});
        SideCube[1][2][1] = new SideBlock(new Side[] {new Side('b', new int[] {0,1,0})});
        SideCube[1][2][2] = new SideBlock(new Side[] {new Side('r', new int[]{0,0,1}), new Side('b', new int[] {0,1,0})});
        // 중앙 면
        
        // 아랫 면
        SideCube[2][0][0] = new SideBlock(new Side[] {new Side('y', new int[]{1,0,0}), new Side('o', new int[]{0,0,-1}), new Side('g', new int[] {0,-1,0})});
        SideCube[2][0][1] = new SideBlock(new Side[] {new Side('y', new int[]{1,0,0}), new Side('g', new int[] {0,-1,0})});
        SideCube[2][0][2] = new SideBlock(new Side[] {new Side('y', new int[]{1,0,0}), new Side('r', new int[]{0,0,1}), new Side('g', new int[] {0,-1,0})});

        SideCube[2][1][0] = new SideBlock(new Side[] {new Side('y', new int[]{1,0,0}), new Side('o', new int[]{0,0,-1})});
        SideCube[2][1][1] = new SideBlock(new Side[] {new Side('y', new int[]{1,0,0})});
        SideCube[2][1][2] = new SideBlock(new Side[] {new Side('y', new int[]{1,0,0}), new Side('r', new int[]{0,0,1})});

        SideCube[2][2][0] = new SideBlock(new Side[] {new Side('y', new int[]{1,0,0}), new Side('o', new int[]{0,0,-1}), new Side('b', new int[] {0,1,0})});
        SideCube[2][2][1] = new SideBlock(new Side[] {new Side('y', new int[]{1,0,0}), new Side('b', new int[] {0,1,0})});
        SideCube[2][2][2] = new SideBlock(new Side[] {new Side('y', new int[]{1,0,0}), new Side('r', new int[]{0,0,1}), new Side('b', new int[] {0,1,0})});
        // 아랫 면
    }

    public static void Lotation(String s) {
        char side = s.charAt(0);
        char dir = s.charAt(1);
        int[] lot = new int[] {1,-1};
        if(dir == '-') {
            lot[0] = -1;
            lot[1] = 1;
        }

        if(side == 'U') {
            SideBlock[][] temp = new SideBlock[3][3];
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    int size = SideCube[0][i][j].block.length;
                    Side[] tempBlock = new Side[size];
                    for(int k=0; k<size; k++) {
                        char tempColor = SideCube[0][j][i].block[k].color;
                        int[] tempDir = SideCube[0][j][i].block[k].dir;
                        tempBlock[k] = new Side(tempColor, new int[] {tempDir[0], tempDir[2]*lot[1], tempDir[1]*lot[0]}); // lotation
                    }
                    temp[(i-1)*lot[1]+1][(j-1)*lot[0]+1] = new SideBlock(tempBlock);
                }
            }
            SideCube[0] = temp;
        } else if(side == 'D') {
            SideBlock[][] temp = new SideBlock[3][3];
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    int size = SideCube[2][i][j].block.length;
                    Side[] tempBlock = new Side[size];
                    for(int k=0; k<size; k++) {
                        char tempColor = SideCube[2][j][i].block[k].color;
                        int[] tempDir = SideCube[2][j][i].block[k].dir;
                        tempBlock[k] = new Side(tempColor, new int[] {tempDir[0], tempDir[2]*lot[0], tempDir[1]*lot[1]}); // lotation
                    }
                    temp[(i-1)*lot[0]+1][(j-1)*lot[1]+1] = new SideBlock(tempBlock);
                }
            }
            SideCube[2] = temp;
        } else if(side == 'F') {
            SideBlock[][] temp = new SideBlock[3][3];
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    int size = SideCube[i][j][2].block.length;
                    Side[] tempBlock = new Side[size];
                    for(int k=0; k<size; k++) {
                        char tempColor = SideCube[i][j][2].block[k].color;
                        int[] tempDir = SideCube[i][j][2].block[k].dir;
                        tempBlock[k] = new Side(tempColor, new int[] {tempDir[1]*lot[0], tempDir[0]*lot[1], tempDir[2]}); // lotation
                    }
                    temp[(j-1)*lot[0]+1][(i-1)*lot[1]+1] = new SideBlock(tempBlock);
                }
            }
            for(int i=0; i<3; i++)
                for(int j=0; j<3; j++)
                    SideCube[i][j][2] = temp[i][j];
        } else if(side == 'B') {
            SideBlock[][] temp = new SideBlock[3][3];
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    int size = SideCube[i][j][0].block.length;
                    Side[] tempBlock = new Side[size];
                    for(int k=0; k<size; k++) {
                        char tempColor = SideCube[i][j][0].block[k].color;
                        int[] tempDir = SideCube[i][j][0].block[k].dir;
                        tempBlock[k] = new Side(tempColor, new int[] {tempDir[1]*lot[1], tempDir[0]*lot[0], tempDir[2]}); // lotation
                    }
                    temp[(j-1)*lot[1]+1][(i-1)*lot[0]+1] = new SideBlock(tempBlock);
                }
            }
            for(int i=0; i<3; i++)
                for(int j=0; j<3; j++)
                    SideCube[i][j][0] = temp[i][j];
        } else if(side == 'L') {
            SideBlock[][] temp = new SideBlock[3][3];
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    int size = SideCube[i][0][j].block.length;
                    Side[] tempBlock = new Side[size];
                    for(int k=0; k<size; k++) {
                        char tempColor = SideCube[i][0][j].block[k].color;
                        int[] tempDir = SideCube[i][0][j].block[k].dir;
                        tempBlock[k] = new Side(tempColor, new int[] {tempDir[2]*lot[0], tempDir[1], tempDir[0]*lot[1]}); // lotation
                    }
                    temp[(j-1)*lot[0]+1][(i-1)*lot[1]+1] = new SideBlock(tempBlock);
                }
            }
            for(int i=0; i<3; i++)
                for(int j=0; j<3; j++)
                    SideCube[i][0][j] = temp[i][j];
        } else if(side == 'R') {
            SideBlock[][] temp = new SideBlock[3][3];
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    int size = SideCube[i][2][j].block.length;
                    Side[] tempBlock = new Side[size];
                    for(int k=0; k<size; k++) {
                        char tempColor = SideCube[i][2][j].block[k].color;
                        int[] tempDir = SideCube[i][2][j].block[k].dir;
                        tempBlock[k] = new Side(tempColor, new int[] {tempDir[2]*lot[1], tempDir[1], tempDir[0]*lot[0]}); // lotation
                    }
                    temp[(j-1)*lot[1]+1][(i-1)*lot[0]+1] = new SideBlock(tempBlock);
                }
            }
            for(int i=0; i<3; i++)
                for(int j=0; j<3; j++)
                    SideCube[i][2][j] = temp[i][j];
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int i=0; i<T; i++) {
            CubeInit();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                lotate = st.nextToken();
                Lotation(lotate);
            }

            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    char t = 0;
                    int size = SideCube[0][k][j].block.length;
                    for(int l=0; l<size; l++) {
                        if (SideCube[0][k][j].block[l].dir[0] == -1) {
                            t = SideCube[0][k][j].block[l].color;
                            break;
                        }
                    }
                    bw.write(t);
                }
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
