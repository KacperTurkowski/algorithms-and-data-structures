
#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>

const int MAXSIZE=100;
const int HASHSIZE=100;

using namespace std;

template <typename T,typename U>
class Set{
public:
    T Union(T set1,T set2){
        T output{};
        for(int i=0;i<set1.Size;i++){
            output.ElementArray[i]=set1.ElementArray[i];
            output.insert(set1.ElementArray[i]);
            output.Size++;
        }
        for(int i=0;i<set2.Size;i++){
            if(output.Member(set2.ElementArray[i])==false){
                output.ElementArray[output.Size]=set2.ElementArray[i];
                output.insert(set2.ElementArray[i]);
                output.Size++;
            }
        }
        return output;
    };
    T Intersection(T set1,T set2){
        T output{};
        for(int i=0;i<set2.Size;i++){
            if(set1.Member(set2.ElementArray[i])) {
                output.ElementArray[i]=set2.ElementArray[i];
                output.insert(set2.ElementArray[i]);
                output.Size++;
            }
        }
        return output;
    };
    T Difference(T set1,T set2){//set1 / set2
        T output{};
        for(int i=0;i<set1.Size;i++) {
            if (set2.Member(set1.ElementArray[i]) == false) {
                output.ElementArray[output.Size] = set1.ElementArray[i];
                output.insert(set1.ElementArray[i]);
                output.Size++;
            }
        }
        return output;
    };
    T Insert(T set,U x){
        if(set.insert(x)) {
            set.ElementArray[set.Size] = x;
            set.Size++;
        }
        else cout<<x<<"already belongs to the set"<<endl;
        return set;
    }
    bool IsMember(T set,U x){
        if(set.Member(x)) return true;
        return false;
    }
    T Pop(T set,U x){
        for(int i=0;i<set.Size;i++){
            if(set.ElementArray[i]==x) {
                for(int j=i;j<set.Size;j++){
                    set.ElementArray[j]=set.ElementArray[j+1];
                }
                set.Size--;
                break;
        }
        }
        auto it = find(set.HashTable[set.hash(x)].begin(),set.HashTable[set.hash(x)].end(), x);
        set.HashTable[set.hash(x)].erase(it);
        return set;
    }
};

class IntegerHash : public Set<IntegerHash,int>{
public:
    int ElementArray[MAXSIZE]{};
    vector<int> HashTable[HASHSIZE];
    int Size=0;

    IntegerHash()= default;
    IntegerHash(int array[],int &size){
        copy(array,array+size,ElementArray);
        for(int i=0;i<size;i++){
            if(insert(array[i])){
                ElementArray[Size]=array[i];
                Size++;
            }
        }
    }
    bool Member(int x){
        vector<int> vectr=HashTable[hash(x)];
        for(auto const& value: vectr){
            if(value==x)return true;
        }
        return false;
    }
    bool insert(int x){
        if(Member(x)) return false;
        int pos=hash(x);
        HashTable[pos].push_back(x);
        return true;
    }
    static int hash(int x){
        return x%HASHSIZE;
    }
    friend ostream& operator<<(ostream& stream,IntegerHash const& ih){
        for(int i=0;i<ih.Size;i++) {
            stream <<ih.ElementArray[i]<<" ";
        }
        stream<<endl;
        return stream;
    }
};
class StringHash : public Set<StringHash,string>{
public:
    string ElementArray[MAXSIZE]{};
    vector<string> HashTable[HASHSIZE];
    int Size=0;

    StringHash()= default;
    StringHash(string array[],int &size){
        copy(array,array+size,ElementArray);
        for(int i=0;i<size;i++){
            if(insert(array[i])){
                ElementArray[Size]=array[i];
                Size++;
            }
        }
    };

    bool Member(const string& x){
        vector<string> vectr=HashTable[hash(x)];
        for(auto const& value: vectr){
            if(value==x)return true;
        }
        return false;
    };
    bool insert(const string& x){
        if(Member(x)) return false;
        int pos=hash(x);
        HashTable[pos].push_back(x);
        return true;
    };
    static int hash(const string& x){
            return ((int)x[0])%HASHSIZE;
    };
    friend ostream& operator<<(ostream& stream,StringHash const& ih){
        for(int i=0;i<ih.Size;i++) {
            stream <<ih.ElementArray[i]<<" ";
        }
        stream<<endl;
        return stream;
    }
};
int main(int argc,char *argv[]) {

    int tab[5]={1,3,5,9,10};
    int x=5;
    IntegerHash set2{tab,x};
    string tab2[5]={"jeden","trzy","piec","dziewiec","dziesiec"};
    x=5;
    StringHash set3{tab2,x};

    int size=argc;
    if(strcmp(argv[1],"integer")==0){
        int IntArray[MAXSIZE];
        for(int i=2;i<size;i++)IntArray[i-2]=stoi(argv[i]);
        size-=2;
        IntegerHash set{IntArray,size};
        cout<<"set: "<<set;
        cout<<"Union: "<<set.Union(set,set2);
        cout<<"Difference: "<<set.Difference(set,set2);
        cout<<"Intersection: "<<set.Intersection(set,set2);
        set=set.Insert(set,6);
        cout<<set;
        cout<<set.IsMember(set,6)<<endl;
        cout<<set.Pop(set,6);
    }
    else if(strcmp(argv[1],"string")==0){
        string StringArray[MAXSIZE];
        for(int i=2;i<size;i++)StringArray[i-2]=argv[i];
        size-=2;
        StringHash set{StringArray,size};
        cout<<"Set: "<<set;
        cout<<"Union: "<<set.Union(set,set3);
        cout<<"Difference: "<<set.Difference(set,set3);
        cout<<"Intersection: "<<set.Intersection(set,set3);
        set=set.Insert(set,"abc");
        cout<<set;
        cout<<set.IsMember(set,"abc")<<endl;
        cout<<set.Pop(set,"abc");
    }
    else cout<<"Wrong arguments"<<endl;
    return 0;
}
