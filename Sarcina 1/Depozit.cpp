#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

using namespace std;
 
void afisareMeniu() {
    cout << "||=============================================================================||" << endl;
    cout << "||                                                                             ||" << endl;
    cout << "||    ██████╗ ███████╗██████╗  ██████╗ ███████╗██╗████████╗██╗   ██╗██╗        ||" << endl;
    cout << "||    ██╔══██╗██╔════╝██╔══██╗██╔═══██╗╚══███╔╝██║╚══██╔══╝██║   ██║██║        ||" << endl;
    cout << "||    ██║  ██║█████╗  ██████╔╝██║   ██║  ███╔╝ ██║   ██║   ██║   ██║██║        ||" << endl;
    cout << "||    ██║  ██║██╔══╝  ██╔═══╝ ██║   ██║ ███╔╝  ██║   ██║   ██║   ██║██║        ||" << endl;
    cout << "||    ██████╔╝███████╗██║     ╚██████╔╝███████╗██║   ██║   ╚██████╔╝███████╗   ||" << endl;
    cout << "||    ╚═════╝ ╚══════╝╚═╝      ╚═════╝ ╚══════╝╚═╝   ╚═╝    ╚═════╝ ╚══════╝   ||" << endl;
    cout << "||                                                                             ||" << endl;
    cout << "||=============================================================================||" << endl;
    cout << "|| 0) Afiseaza depozitul                                                       ||" << endl;
    cout << "|| 1) Inscrie un nou tunel marginal                                            ||" << endl;
    cout << "|| 2) Rescrie un tunel existent                                                ||" << endl;
    cout << "|| 3) Inscrie depozitul in format txt                                          ||" << endl;
    cout << "|| 4) Randul cu numarul maximal de zone ocupate                                ||" << endl;
    cout << "|| 5) Afiseaza coloanele sortate crescator dupa numarul de zone ocupate        ||" << endl;
    cout << "|| 6) Adresele zonelor fara vecini ocupati                                     ||" << endl;
    cout << "|| 7) Zona cu locuri libere in perimetru                                       ||" << endl;
    cout << "|| 8) Problema propusa                                                         ||" << endl;
    cout << "||=============================================================================||" << endl;
    cout << endl;
}
 
int inputInt()
{
    int x;
    while (!(cin >> x))
    {
        cin.clear();
        cin.ignore(10000, '\n');
        cout << "Input invalid! Reintroduceti numarul: " << endl;
    }
    return x;
}
 
void afiseazaDepozit(vector<vector<int>> depozit) {
    for(int i = 0; i < depozit.size(); i++) {
        for(int j = 0; j < depozit[i].size(); j++) {
            cout << depozit[i][j] << " ";
        }
        cout << endl;
    }
}
 
void inscrieTunel(vector<vector<int>> &depozit) {
    string directie;
    cout << "Introduceti directia tunelului(N, S, E, W): ";
    cin >> directie;
    while(directie != "N" && directie != "S" && directie != "E" && directie != "W") {
        cout << "Directia introdusa nu este valida!" << endl;
        cout << "Introduceti directia tunelului(N, S, E, W): ";
        cin >> directie;
    }
    if(directie == "N") {
        depozit.insert(depozit.begin(), vector<int>(depozit[0].size()));
    } else if (directie == "S") {
        depozit.push_back(vector<int>(depozit[0].size()));
    } else if (directie == "E") {
        for(int i = 0; i < depozit.size(); i++) {
            depozit[i].push_back(0);
        }
    } else if (directie == "W") {
        for(int i = 0; i < depozit.size(); i++) {
            depozit[i].insert(depozit[i].begin(), 0);
        }
    }
    cout << endl << "Depozitul a fost modificat cu succes!" << endl << endl;
}
 
void rescrieTunel(vector<vector<int>> &depozit) {
    string tip;
    cout << "Introduceti tipul tunelului(coloana, rand): ";
    cin >> tip;
    while(tip != "coloana" && tip != "rand") {
        cout << "Tipul introdus nu este valid!" << endl;
        cout << "Introduceti tipul tunelului(coloana, rand): ";
        cin.clear();      
        cin >> tip;
    }
    if(tip == "coloana") {
        afiseazaDepozit(depozit);
        cout << "Introdu indicele coloanei(max. indice " << depozit[0].size()-1 << "): ";
    } else if (tip == "rand") {
        afiseazaDepozit(depozit);
        cout << "Introdu indicele randului(max. indice " << depozit.size()-1 << "): ";
    }
 
    int idx;
    bool valid = false;
    while(!valid) {
        idx = inputInt();
        valid = true;
        if(idx < 0) {
            cout << "Indicele introdus nu poate fi negativ!" << endl;
            valid = false;
            continue;
        }
        if(tip == "coloana") {
            if(idx >= depozit.size()) {
                cout << "Indicele depaseste nr de coloane(" << depozit[0].size()-1 << ")! Reintrodu indecele: " << endl;
                valid = false;
                continue;
            }
        } else if(tip == "rand") {
            if(idx >= depozit[0].size()) {
                cout << "Indicele depaseste nr de randuri(" << depozit.size() - 1 << ")!  Reintrodu indecele: " << endl;
                valid = false;
                continue;
            }
        }
        if(valid) break;
    }
 
    if(tip == "coloana") {
        cout << "Reintroduceti informatia tunelului(doar 0 sau 1): ";
        for(int i = 0; i < depozit.size(); i++) {
            int x = inputInt();
            while(x != 0 && x != 1) {
                cout << "Valoarea introdusa trebuie sa fie 0 sau 1!" << endl;
                x = inputInt();
            }
            depozit[i][idx] = x;
        }
    } else if (tip == "rand") {
        cout << "Reintroduceti informatia tunelului: ";
        for(int i = 0; i < depozit[0].size(); i++) {
            int x = inputInt();
            while (x != 0 && x != 1)
            {
                cout << "Valoarea introdusa trebuie sa fie 0 sau 1!" << endl;
                x = inputInt();
            }
            depozit[idx][i] = x;
        }
    }
    
    cout << endl << "Depozitul a fost modificat cu succes!" << endl;
}
 
void inscrieDepozit(vector<vector<int>> depozit) {
    ofstream fout("./Binar.txt");
    for(int i = 0; i < depozit.size(); i++) {
        for(int j = 0; j < depozit[i].size(); j++) {
            fout << depozit[i][j] << " ";
        }
        fout << endl;
    }
    fout.close();
 
    cout << "Depozitul a fost inscris cu success!" << endl << endl;
}
 
void maxRand(vector<vector<int>> depozit) {
    int max = 0;
    int maxidx = 0;
    for(int i = 0; i < depozit.size(); i++) {
        int sum = 0;
        for(int j = 0; j < depozit[i].size(); j++) {
            sum += depozit[i][j];
        }
        if(sum > max) {
            max = sum;
            maxidx = i;
        }
    }
    cout << "Randul cu cele mai multe zone depozitare ocupate este randul " << maxidx+1 << ": ";
    for(int i = 0;i<depozit[0].size();i++) {
        cout << depozit[maxidx][i] << " ";
    }
    cout << "(cu " << max << " zone ocupate)." << endl << endl;
}

int coloanaZoneOcupate(vector<vector<int>> depozit, int idx) {
    int sum = 0;
    for(int i = 0;i<depozit.size();i++) {
        sum += depozit[i][idx];
    }
    return sum;
}

void afisareCrescatorColoane(vector<vector<int>> depozit) {
    vector<int> coloane;
    for(int i = 0;i<depozit[0].size();i++) {
        coloane.push_back(i);
    }

    for(int i = 1; i < coloane.size(); i++) {
        int key = coloane[i];
        int j = i-1;
        while(j >= 0 && coloanaZoneOcupate(depozit, coloane[j]) > coloanaZoneOcupate(depozit, key)) {
            coloane[j+1] = coloane[j];
            j--;
        }
        coloane[j+1] = key;
    }
    cout << "Coloanele sortate crescator dupa numarul de zone ocupate sunt: " << endl;
    for(int i = 0;i<depozit.size();i++) {
        for(int j = 0;j<coloane.size();j++) {
            cout << depozit[i][coloane[j]] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

void zoneFaraVeciniOcupati(vector<vector<int>> depozitCopie, vector<vector<int>> &depozit) {
    for(int i = 0;i<depozit.size();i++) {
        for(int j = 0;j<depozit[i].size();j++) {
            if(depozit[i][j] == 1) {
                if(i > 0) {
                    depozitCopie[i-1][j] = 1;
                }
                if(i < depozit.size()-1) {
                    depozitCopie[i+1][j] = 1;
                }
                if(j > 0) {
                    depozitCopie[i][j-1] = 1;
                }
                if(j < depozit[i].size()-1) {
                    depozitCopie[i][j+1] = 1;
                }
            }
        }
    }

    cout << "Adresele zonelor fara vecini ocupati sunt: " << endl;
    for(int i = 0;i<depozit.size();i++) {
        for(int j = 0;j<depozit[i].size();j++) {
            if(depozitCopie[i][j] == 0) {
                cout << "Randul: " << i+1 << "; Coloana: " << j+1 << endl;
            }
        }
    }
    cout << endl;
}

void maxDreptunghi(vector<vector<int>> depozit)
{
    int n = depozit.size();
    int m = depozit[0].size();

    int maxArie = 0;
    int xSus, ySus, xJos, yJos;

    for (int i = 0; i < n; ++i)
    {
        for (int j = 0; j < m; ++j)
        {
            if (depozit[i][j] == 0)
            {
                int x = j;
                int y = i;
                while (x < m && depozit[i][x] == 0)
                    x++;
                while (y < n && depozit[y][j] == 0)
                    y++;

                // Verificăm dacă zona are doar 0 pe perimetru
                bool valid = true;
                for (int k = j; k < x; ++k)
                {
                    if (depozit[i][k] != 0 || depozit[y - 1][k] != 0)
                    {
                        valid = false;
                        break;
                    }
                }
                for (int k = i; k < y; ++k)
                {
                    if (depozit[k][j] != 0 || depozit[k][x - 1] != 0)
                    {
                        valid = false;
                        break;
                    }
                }

                if (valid)
                {
                    int arie = (x - j) * (y - i);
                    if (arie > maxArie)
                    {
                        maxArie = arie;
                        xSus = j;
                        ySus = i;
                        xJos = x;
                        yJos = y;
                    }
                }
            }
        }
    }

    std::cout << "Aria maximă: " << maxArie << std::endl;
    std::cout << "Coordonatele punctelor: (" << xSus+1 << ", " << ySus+1 << "), (" << xJos << ", " << yJos << ")" << std::endl;
}

vector<vector<int>> bfs(vector<vector<int>> depozit, int a, int b, int c, int d, vector<vector<int>> drum, vector<vector<bool>> vizitat) {
    vizitat[a][b] = true;

    if(a == c && b == d) {
        return drum;
    }

    vector<vector<int>> stanga;
    vector<vector<int>> dreapta;
    vector<vector<int>> sus;
    vector<vector<int>> jos;


    if(a > 0 && !vizitat[a-1][b] && depozit[a-1][b] == 0) {
        drum.push_back({a-1, b});
        stanga = bfs(depozit, a-1, b, c, d, drum, vizitat);
        drum.pop_back();
    }
    if(b > 0 && !vizitat[a][b-1] && depozit[a][b-1] == 0) {
        drum.push_back({a, b-1});
        sus = bfs(depozit, a, b-1, c, d, drum, vizitat);
        drum.pop_back();
    }
    if(a < depozit.size()-1 && !vizitat[a+1][b] && depozit[a+1][b] == 0) {
        drum.push_back({a+1, b});
        dreapta = bfs(depozit, a+1, b, c, d, drum, vizitat);
        drum.pop_back();
    }
    if(b < depozit[0].size()-1 && !vizitat[a][b+1] && depozit[a][b+1] == 0) {
        drum.push_back({a, b+1});
        jos = bfs(depozit, a, b+1, c, d, drum, vizitat);
        drum.pop_back();
    }

    /*
        stanga - 0
        dreapta - 1
        sus - 2
        jos - 3
    */

    vector<string> posibile = {"stanga", "dreapta", "sus", "jos"};    
    for(int i = 0;i<posibile.size();i++) {
        if(posibile[i] == "stanga" && stanga.back()[0] == c && stanga.back()[1] == d) 
        {
            posibile.erase(posibile.begin() + i);
        }
        else if (posibile[i] == "dreapta" && dreapta.back()[0] == c && stanga.back()[1] == d) 
        {
            posibile.erase(posibile.begin() + i);
        }
        else if (posibile[i] == "sus" && sus.back()[0] == c && stanga.back()[1] == d)
        {
            posibile.erase(posibile.begin() + i);
        }
        else if (posibile[i] == "jos" && jos.back()[0] == c && stanga.back()[1] == d)
        {
            posibile.erase(posibile.begin() + i);
        }
    }

    if(posibile.size() == 0) {
        return drum;
    }

    int min_ = 1000000000;
    string minidx;
    for(int i = 0;i<posibile.size();i++) {
        if(posibile[i] == "stanga") {
            if(stanga.size() < min_) {
                min_ = stanga.size();
                minidx = "stanga";
            }
        } else if(posibile[i] == "dreapta") {
            if(dreapta.size() < min_) {
                min_ = dreapta.size();
                minidx = "dreapta";
            }
        } else if(posibile[i] == "sus") {
            if(sus.size() < min_) {
                min_ = sus.size();
                minidx = "sus";
            }
        } else if(posibile[i] == "jos") {
            if(jos.size() < min_) {
                min_ = jos.size();
                minidx = "jos";
            }
        }
    }

    if(minidx == "stanga") {
        return stanga;
    } else if(minidx == "dreapta") {
        return dreapta;
    } else if(minidx == "sus") {
        return sus;
    } else if(minidx == "jos") {
        return jos;
    }

    return drum;
}


void problemaPropusa(vector<vector<int>> depozit) {
    cout << "Reprezentarea initiala a depozitului este: " << endl;
    afiseazaDepozit(depozit);
    cout << endl;

    int a, b, c, d;

    cout << "Introduceti coordonatele pozitiei initiale(pozitia initiala obligator este un spatiu liber):" << endl;
    while(true) {
        a = inputInt() - 1;
        b = inputInt() - 1;

        if (a < 0 || b < 0 || a >= depozit.size() || b >= depozit[0].size())
        {
            cout << "Pozitia finala trebuie sa fie in depozit(1-" << depozit.size() << ", 0-" << depozit[0].size() << ")! Reintroduceti coordonatele: " << endl;
            continue;
        }
        if(depozit[a][b] == 1) {
            cout << "Pozitia introdusa este ocupata! Reintroduceti coordonatele: " << endl;
            continue;
        }
        break;
    }

    cout << "Introduceti coordonatele pozitiei finale(pozitia initiala obligator este un spatiu liber):" << endl;
    while (true)
    {
        c = inputInt() - 1;
        d = inputInt() - 1;

        if(c < 0 || d < 0 || c >= depozit.size() || d >= depozit[0].size()) 
        {
            cout << "Pozitia finala trebuie sa fie in depozit(1-" << depozit.size() << ", 1-" <<  depozit[0].size() << ")! Reintroduceti coordonatele: " << endl;
            continue;
        } 
        else if (depozit[c][d] == 1)
        {
            cout << "Pozitia introdusa este ocupata! Reintroduceti coordonatele: " << endl;
            continue;
        } 
        else if(a >= c && b >= d) 
        {
            cout << "Pozitia finala trebuie sa fie mai la stanga si mai jos de pozitia initiala! Reintroduceti coordonatele: " << endl;
            continue;
        }
        break;
    }
    vector<vector<int>> drum;
    drum.push_back({a,b});
    vector<vector<bool>> vizitat(depozit.size(), vector<bool>(depozit[0].size(), false));
    vector<vector<int>> rezultat = bfs(depozit, a, b, c, d, drum, vizitat);

    if(rezultat.back()[0] == c && rezultat.back()[1] == d) {
        cout << "Lungimea drumului minim este: " << rezultat.size() << endl;
        cout << "Coordonatele drumului sunt: " << endl;
        for(int i =0;i<rezultat.size();i++) {
            cout << rezultat[i][0] + 1 << " " << rezultat[i][1] + 1 << endl;
        }
    } else {
        cout << "Nu se poate ajunge din pozitia initiala la pozitia finala!" << endl;
    }
}


int main() {
    ifstream fin("./Depozit.in");
    int n, m, k;
    fin >> n >> m >> k;

    vector<vector<int>> depozit(n, vector<int>(m));

    for(int i = 0; i < k; i++) {
        int x, y;
        fin >> x >> y;
        depozit[x - 1][y - 1]++;
    }

    int option = -1;
    while(true) {
        afisareMeniu();
        option = inputInt();
        cout << endl;
        switch(option) {
            case 0:
                afiseazaDepozit(depozit);
                break;
            case 1:
                inscrieTunel(depozit);
                break;
            case 2:
                rescrieTunel(depozit);
                break;
            case 3:
                inscrieDepozit(depozit);
                break;
            case 4:
                maxRand(depozit);
                break;
            case 5:
                afisareCrescatorColoane(depozit);
                break;            
            case 6:
                zoneFaraVeciniOcupati(depozit, depozit);
                break;
            case 7:
                maxDreptunghi(depozit);
                break;
            case 8:
                problemaPropusa(depozit);
                break;
            default:
                cout << "Optiune invalida!" << endl;
        }
        cout << "Apasati enter pentru a continua..." << endl;
        getchar();
        getchar();
        system("cls");
    }

    return 0;
}
