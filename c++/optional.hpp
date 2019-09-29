#ifndef DATA_STRUCTURES_CONTROL_FLOW_MONAD_OPTIONAL_H_
#define DATA_STRUCTURES_CONTROL_FLOW_MONAD_OPTIONAL_H_


template<typename T>
class optional {

        optional<int> empty;
        T* value;
        T& value_rfc;

        //flags
        bool is_present = false;
        bool is_rfc = false;

    public:
        optional(T value) {
            this->value = value;
            this->is_present = true;
        }

        optional(T* value) {
            this->value = value;
            this->is_rfc = false;
            this->is_present = true;
        }

        optional(T& value) {
            this->value_rfc = value;
            this->is_rfc = true;
            this->is_present = true;
        }

        bool is_present() {

        }

        T get() {

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
            if(container.is_present()) {
                this->value = container.get();
                // etc... pass all values
            }
        }

        // optional<T>
};