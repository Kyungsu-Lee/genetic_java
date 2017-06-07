from class_randassign import *

#################################
# set the number of first generation
first_gen_num = 20
####################################

for i in range(first_gen_num):
    main(classroom_df, class_df)
    class_df.to_csv("data/" + folder + "/class_randassign"+str(i+1+80)+".csv", index=False, index_col=False)
