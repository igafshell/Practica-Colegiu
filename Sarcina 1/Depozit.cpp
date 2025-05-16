#include <iostream>
#include <vector>
#include <fstream>
 
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
        for(int i = 0;i<depozit.size();i++) {
            for(int j = 0;j<depozit[0].size();j++) {
                cout << depozit[i][j] << " ";
            }
            cout << endl;
        }
        cout << "Introdu indicele coloanei(max. indice " << depozit[0].size()-1 << "): ";
    } else if (tip == "rand") {
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
        while(j >= 0 && coloanaZoneOcupate(depozit, coloane[j]) < coloanaZoneOcupate(depozit, key)) {
            coloane[j+1] = coloane[j];
            j--;
        }
        coloane[j+1] = key;
    }
    cout << "Coloanele sortate crescator dupa numarul de zone ocupate sunt: ";
    for(int i = 0;i<depozit.size();i++) {
        for(int j = 0;j<coloane.size();j++) {
            cout << depozit[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
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
            default:
                cout << "Optiune invalida!" << endl;
        }
        cout << "Apasati enter pentru a continua..." << endl;
        getchar();
        system("cls");
    }

    return 0;
}
