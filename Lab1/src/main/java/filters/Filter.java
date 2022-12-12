package filters;

import insurance.Insurance;

public interface Filter {
    boolean check(Insurance insurance);
}