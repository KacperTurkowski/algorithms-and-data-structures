#include <iostream>
using namespace std;
const int maxlegth=20;
typedef int elementtype;
typedef int position;
class Stack
{
    int S[maxlegth] ;
    position Top;
public:
    Stack(){
        Top=-1;
    };
    void Push(elementtype x);
    void Pop();
    bool Empty();
    elementtype TopElem();
    void Makenull();
};
void Stack::Push(elementtype x) {
    if (Top + 1 < 20) {
    Top++;
    S[Top] = x;
    }
    else cout<<"Nie ma miejsca w tablicy";
}
void Stack::Pop() {
    if(Top!=-1){
        Top--;
    }
}
bool Stack::Empty() {
    if(Top==-1) return true;
    else return false;
}

elementtype Stack::TopElem() {
    if(Empty()==true) return 1000000;
    else return S[Top];
}

void Stack::Makenull() {
    Top=-1;
}

int main() {
    Stack stos{};
    cout<<stos.Empty()<<endl;
    stos.Push(5);
    cout<<stos.TopElem()<<endl;
    cout<<stos.Empty()<<endl;
    stos.Pop();
    cout<<stos.Empty()<<endl;
    stos.Push(2);
    stos.Push(3);
    cout<<stos.TopElem()<<endl;
    stos.Makenull();
    cout<<stos.Empty()<<endl;
    return 0;
}