Platform Requirement: Windows xp/vista/7/8

======================================
Usage: type the below in command line

collate [root dir1] [root dir2] [extensions] # as many root dirs as you want

e.g. collate "C:\Users\Jason\Desktop\collate v2\input\srcA" "C:\Users\Jason\Desktop\collate v2\input\srcB" h,cpp,java

(PS: don't forget to double quote folder paths.)



======================================
Sample input:

{start of file}--------------

	code			// not collated
	code			// not collated
	comments		// not collated
	code			// not collated
	other stuff		// not collated


	//@author A0909865T
	public void fooBar() {	// added to collated/A0909865T.col
				// added to collated/A0909865T.col
	}			// added to collated/A0909865T.col
				// added to collated/A0909865T.col
				// added to collated/A0909865T.col
	//@author A0702345U
	public void bazQux() {	// added to collated/A0702345U.col
				// added to collated/A0702345U.col
	}			// added to collated/A0702345U.col
				// added to collated/A0702345U.col
{end of file}----------------


Sample output from the above input:
collated/A0909865T.col 
collated/A0702345U.col