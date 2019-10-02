#ifndef DATA_STRUCTURES_CONTROL_FLOW_MONAD_EITHER_H_
#define DATA_STRUCTURES_CONTROL_FLOW_MONAD_EITHER_H_

// TODO: find out why namespace std fails
// using namespace std; 

#include <iostream>

template <typename A, typename B>
class either
{
  private:
    const A* element1 = nullptr;
    const B* element2 = nullptr;

    // bool is_array = false;

  public: 

    either(A element) {
        element1 = element;
        element2 = nullptr;
    }
    either(B element) {
        element2 = element;
        element1 = nullptr;
    }
    either(A& element) {
        element1 = element;
        element2 = nullptr;
    }
    either(B& element) {
        element2 = element;
        element1 = nullptr;
    }
    ~either() {
        delete element1;
        delete element2;
    }

    // void tag_element_as_array(bool is_array) {
    //     this->flag_as_array = is_array;
    // }
    

    static either<A, B> of(A element) {
        return new either<A, B>(element);
    }

    static either<A, B> of(B element) {
        return new either<A, B>(element);
    }

    // operators
    void operator=(A element) {
        element1 = element;
        element2 = nullptr;
    }

    void operator=(B element) {
        element2 = element;
        element1 = nullptr;
    }

    // TODO: create error codes and error description
    template<typename T> T getValue()
    {
        if (element1 != nullptr && element2 == nullptr) {
            return (T)element1;
        }

        if (element1 == nullptr && element2 != nullptr) {
            return (T)element2;
        }

        return (T*)::operator new(1);
    }
};

#endif