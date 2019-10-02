#ifndef DATA_STRUCTURES_CSTRING_H_
#define DATA_STRUCTURES_CSTRING_H_

#include <stdint.h>

using namespace std;

 class cstring {
        char* char_container = nullptr;
        long size = 0;
        
        void add_array_of_chars(const char* chars) {
            if(chars != nullptr) {
                this->char_container = new char[sizeof(chars)];
                this->char_container[0] = '\0';
                for(int index= 0; index < sizeof(chars); ++index) {
                    if(chars[index] != '\0') {
                        this->char_container[index] = chars[index]; 
                    }
                }
            } else {
                this->char_container = new char[sizeof(chars)];
                this->char_container[0] = '\0';
            }
        }
            
        void concat(const char* chars) {
            if(chars != nullptr) {
                this->size = this->size+sizeof(chars);
                char* temp = new char[this->size];
                long new_chars_size = sizeof(chars);
                
                for(int index= 0; index < this->size; ++index) {
                    if(index < new_chars_size && chars[index] != '\0') {
                        this->char_container[index] = chars[index];
                    } else if(chars[index] != '\0') {
                        temp[index] = chars[index];
                    }
                }
            }
        }
        
    public:
    
        // Constructors
        cstring() {
            this->char_container = new char[0];
        }
        cstring(const char* chars) {
            this->add_array_of_chars(chars);
        }
        cstring(const char& chars) {
            this->size = sizeof(chars);
            // this->char_container = chars;
        }

        ~cstring() {
            delete[] this->char_container;
        }
        
        // Operators
        
        void operator =(const char* chars) {
            this->add_array_of_chars(chars);
        }
        
        void operator =(const char& chars) {
            // this->add_dynamic_array_of_chars( *(chars) );
        }
        
        void operator =(cstring chars) {
            if(chars.length() > 0) {
                for(int index = 0; index < chars.length(); ++index) {
                    this->char_container[ (this->size+=1) ] = chars.char_at(index); 
                }
            }
        }
        
        void operator +(const char* chars) {
            this->concat(chars);
        }
        
        void operator +(const char& chars) {
            // this->concat(chars);
        }
        
        void operator +=(const char* chars) {
            this->concat(chars);
        }
        
        void operator +=(const char& chars) {
            // std::copy(chars, )
            // this->concat(chars);
        }
        
        bool operator ==(cstring value) {
            if(value.length() > this->size || value.length() < this->size) {
                return false;
            }
            for(int index = 0; index < value.length(); ++index) {
                if(this->char_container[index] != value.char_at(index)) {
                    return false;
                }
            }

            return true;
        }

        // Methods
        void append(const char* chars) {
            this->concat(chars);
        }
        
        void append(const char& chars) {
            // this->concat(*chars);
        }
        
        

        char char_at(int index) {
            if(index > 0 && index < this->size) {
                return this->char_container[index];
            }
            return char();
        }

        int length() {
            return (int)this->size;
        }
};

#endif
