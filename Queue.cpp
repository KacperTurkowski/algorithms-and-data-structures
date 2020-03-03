#include <iostream>

using namespace std;

const int maxlength=50;
typedef int elementtype;
typedef int position;

class Kolejka
{
protected :
    elementtype Queue[maxlength]{};
    position Front; // Index of Head element
    position Rear; // Index of last element
public:
    Kolejka(){
        Front=0;
        Rear=maxlength-1;
    };
    static int AddOne(int i);
    void Enqueue(elementtype x);
    void Dequeue();
    elementtype FrontElem();
    void Makenull();
    bool Empty();
};
void Kolejka::Enqueue(elementtype x)
{
    if ( AddOne(AddOne(Rear)) != Front)
                {
                        Rear = AddOne(Rear);
                Queue[Rear] = x;
                }
}
void Kolejka ::Dequeue()
{
    if (Empty()==0)
    {
        Front= AddOne(Front);
    }
}
void Kolejka::Makenull()
{
    Front=0;
    Rear= maxlength-1;
}
elementtype Kolejka::FrontElem()
{
    if (!Empty())
        return Queue[Front];
    else return 100000000;
}
int Kolejka::AddOne(int i) {
    return ((i + 1) % maxlength);
}
bool Kolejka::Empty()
{
    return (AddOne(Rear) == Front);
}
int main()
{
    Kolejka kolejka{};
    cout<<"Czy kolejka pusta"<<kolejka.Empty()<<endl;
    cout<<"wstawiam 5"<<endl;
    kolejka.Enqueue(5);
    cout<<"Czy kolejka pusta "<<kolejka.Empty()<<endl;
    cout<<"Pierwszy element: "<<kolejka.FrontElem()<<endl;
    cout<<"usuwam element"<<endl;
    kolejka.Dequeue();
    cout<<"Czy kolejka pusta "<<kolejka.Empty()<<endl;
    cout<<"wstawiam: 4"<<endl;
    kolejka.Enqueue(4);
    cout<<"wstawiam: 3"<<endl;
    kolejka.Enqueue(3);
    cout<<"wstawiam: 2"<<endl;
    kolejka.Enqueue(2);
    cout<<"Pierwszy element: "<<kolejka.FrontElem()<<endl;
    cout<<"wstawiam: 1"<<endl;
    kolejka.Enqueue(1);
    cout<<"Pierwszy element: "<<kolejka.FrontElem()<<endl;
    cout<<"usuwam element"<<endl;
    kolejka.Dequeue();
    cout<<"Pierwszy element :"<<kolejka.FrontElem()<<endl;
    cout<<"wstawiam: 6"<<endl;
    kolejka.Enqueue(6);
    cout<<"Pierwszy element: "<<kolejka.FrontElem()<<endl;
    cout<<"usuwam element"<<endl;
    kolejka.Dequeue();
    cout<<"usuwam element"<<endl;
    kolejka.Dequeue();
    cout<<"Pierwszy element :"<<kolejka.FrontElem()<<endl;
    cout<<"makenull"<<endl;
    kolejka.Makenull();
    cout<<"Czy kolejka pusta "<<kolejka.Empty()<<endl;
    return 0;
}
