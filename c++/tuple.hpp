#ifndef DATA_STRUCTURES_CONTAINER_TUPLE_H_
#define DATA_STRUCTURES_CONTAINER_TUPLE_H_

#include <string>
#include <iostream>

template<typename L, typename R> 
class tuple {
        L* left;
        R* right;

        tuple(L left, R right) {

        }
    public:
        ~tuple() {
            delete left;
            delete right;
        }

        // value extraction
        //=====================================================
        L get_left() { return param1; }
        R get_right() { return param2; }

        static tuple<L, R> of(A left, B right) {
            return new tuple(left, right);
        }

        void print() { 
            param1.print();
            param2.print(); 
        }

        void operator =(L left) { this->left = left; }
        void operator =(R right) { this->right = right; } 

        
        L operator [](int index) { 
            if(index == 1)  {
                return this->left;
            } else {
                return;
            }
        }

        R operator [](int index) {
            if(index == 2) {
                return this->right;
            } else {
                return;
            }
        }

};

#endif