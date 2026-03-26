#include <iostream>

using namespace std;

double raiz_manual(double n) {
    if (n < 0) return -1;
    if (n == 0) return 0;
    double x = n;
    double y = 1;
    double e = 0.000001;
    while (x - y > e) {
        x = (x + y) / 2;
        y = n / x;
    }
    return x;
}

double mru_velocidad(double d, double t) { return d / t; }
double mru_distancia(double v, double t) { return v * t; }
double mru_tiempo(double d, double v) { return d / v; }
double mru_posicion_final(double xi, double v, double t) { return xi + (v * t); }

double mrua_vf(double vi, double a, double t) { return vi + (a * t); }
double mrua_distancia(double vi, double a, double t) { return (vi * t) + (0.5 * a * t * t); }
double mrua_vf_torricelli(double vi, double a, double d) {
    double vf_cuadrado = (vi * vi) + (2 * a * d);
    return raiz_manual(vf_cuadrado);
}
double mrua_distancia_media(double vi, double vf, double t) { return ((vi + vf) / 2.0) * t; }

int main() {
    double xi, v, t, xf, d, vi, vf, a;
    int opcion, sbOpcion;

    cout << "-- Calculadora Cinematica --" << endl;
    cout << "\n 1. M.R.U \n 2. M.R.U.A" << endl;
    cout << "Ingresa una opcion: ";
    cin >> opcion;

    if (opcion == 1) {
        cout << "\n1. Velocidad\n2. Distancia\n3. Tiempo\n4. Posicion Final" << endl;
        cin >> sbOpcion;
        if (sbOpcion == 1) {
            cout << "Distancia (m): "; cin >> d;
            cout << "Tiempo (s): "; cin >> t;
            cout << "Velocidad: " << mru_velocidad(d, t) << " m/s" << endl;
        } else if (sbOpcion == 2) {
            cout << "Velocidad (m/s): "; cin >> v;
            cout << "Tiempo (s): "; cin >> t;
            cout << "Distancia: " << mru_distancia(v, t) << " m" << endl;
        } else if (sbOpcion == 3) {
            cout << "Distancia (m): "; cin >> d;
            cout << "Velocidad (m/s): "; cin >> v;
            cout << "Tiempo: " << mru_tiempo(d, v) << " s" << endl;
        } else if (sbOpcion == 4) {
            cout << "Posicion inicial: "; cin >> xi;
            cout << "Velocidad: "; cin >> v;
            cout << "Tiempo: "; cin >> t;
            cout << "Posicion Final: " << mru_posicion_final(xi, v, t) << " m" << endl;
        }
    } else if (opcion == 2) {
        cout << "\n1. Velocidad Final (vi, a, t)\n2. Distancia (vi, a, t)\n3. Velocidad Final (vi, a, d)\n4. Distancia Media (vi, vf, t)" << endl;
        cin >> sbOpcion;
        if (sbOpcion == 1) {
            cout << "vi: "; cin >> vi; cout << "a: "; cin >> a; cout << "t: "; cin >> t;
            cout << "vf: " << mrua_vf(vi, a, t) << " m/s" << endl;
        } else if (sbOpcion == 2) {
            cout << "vi: "; cin >> vi; cout << "a: "; cin >> a; cout << "t: "; cin >> t;
            cout << "d: " << mrua_distancia(vi, a, t) << " m" << endl;
        } else if (sbOpcion == 3) {
            cout << "vi: "; cin >> vi; cout << "a: "; cin >> a; cout << "d: "; cin >> d;
            double res = mrua_vf_torricelli(vi, a, d);
            if (res != -1) cout << "vf: " << res << " m/s" << endl;
            else cout << "Error: Raiz imaginaria" << endl;
        } else if (sbOpcion == 4) {
            cout << "vi: "; cin >> vi; cout << "vf: "; cin >> vf; cout << "t: "; cin >> t;
            cout << "dm: " << mrua_distancia_media(vi, vf, t) << " m" << endl;
        }
    }

    return 0;
}