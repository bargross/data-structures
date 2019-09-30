#ifndef DATA_STRUCTURES_CONTROL_FLOW_MONAD_OPTIONAL_H_
#define DATA_STRUCTURES_CONTROL_FLOW_MONAD_OPTIONAL_H_


template<typename T>
class optional {

        template<typename C> struct content { T* value; };
        content<int> nothing;
        content<T> container;

        //flags
        bool is_value_present = false;

    public:
        optional() {
            this->container.value = nullptr;
            this->nothing.value = 0;
        }

        optional(T value) {
            this->value = value;
            this->is_value_present = true;
        }

        optional(T* value) {
            this->value = value;
            this->is_value_present = true;
        }

        optional(T& content) {
            this->container.value = content;
            this->is_value_present = true;
        }

        optional(const optional<T>& container) {
            this->value = container.get();
            this->is_value_present = container.is_present();
        }

        bool is_present() {
            return this->is_present;
        }

        T get() {
            if(this->is_value_present) {
                return this->value;
            } 

            // else throw exception
        }

        optional<T> of(T value) {
            return new optional<T>(value);
        }

        optional<T> of(T* value) {
            return new optional<T>(value);
        }

        optional<T> of(T& value) {
            return new optional<T>(value);
        }

        void operator new(const optional<T> container) {
            void* dynamic_mem = new optional<T>(container);
            return dynamic_mem;
        }

        // optional<T>
};

#endif