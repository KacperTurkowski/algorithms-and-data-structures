#include <iostream>

using namespace std;

typedef int elementtype;
struct celltype {
        elementtype element;
        celltype * next;
    };
typedef celltype * position;
class Lista {
    protected :
        position l; // return pointer to head of the list
    public:
        Lista(){
            l=new celltype;
            l->next=nullptr;
        }; //Constructor
        void Insert(elementtype x, position p);
        void Delete(position p);
        elementtype Retrieve(position p);
        position Locate(elementtype x);
        position First();
        position Next(position p);
        position Previous(position p);
        position END();
    };
void print(Lista l)
{
    position i=l.First();
    while (i!=l.END())
    {
        printf ("  %d,", l.Retrieve(i));
        i=l.Next(i);
    }
    printf("\n");

}
    int main(){
        Lista *list =new Lista();
        list->Insert(100,list->First());
        print (*list);
        for (int i=0; i<3;i++)
            list->Insert(i,list->First());
        print (*list);
        list->Insert (20,list->Previous(list->END()));
        print(*list);
        list->Delete( list->Locate(20));
        print(*list);
        return 0;
    }


void Lista::Insert(elementtype x, position p) {
    position n=new celltype;
    n->next=p->next;
    p->next=n;
    n->element=x;
}
void Lista::Delete(position p) {//Delete element from position p
    position tmp;
    tmp=p->next;
    p->next=p->next->next;
    delete tmp;
    }

elementtype Lista::Retrieve(position p) {//return element on position p
    if (p->next!=nullptr)
        return p->next->element;
}

position Lista::Locate(elementtype x) {
    position tmp;
    tmp=l;
    while (tmp->next!=nullptr)
    {
        if (tmp->next->element==x) return tmp;

        tmp=tmp->next;
    }
    return tmp;
}

position Lista::First() {
    return l;
}
position Lista::Next(position p) {//return next position after p
    return p->next;

}

position Lista::Previous(position p) {
    position tmp;
    tmp=l;
    while (tmp->next!=p) tmp=tmp->next;
    return tmp;
}

position Lista::END() {
    position tmp;
    tmp=l;
    while (tmp->next!=nullptr) tmp=tmp->next;
    return tmp;
}
